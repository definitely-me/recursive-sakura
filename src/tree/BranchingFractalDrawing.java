package tree;

import core.Drawing;
import utils.Interval;
import utils.Utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.SplittableRandom;

public class BranchingFractalDrawing extends Drawing {

    private final BranchingFractalDrawingConfig conf;
    private final long seed;
    private SplittableRandom random;                             // отсутствует потокобезопасность -> быстре, чем Random в несколько раз
    private final Path2D.Float branchShape = new Path2D.Float(); // мутирующий обьект, чтобы не плодить обьект для каждой ветки

    private final static float BRANCH_TAPER_FACTOR = 0.7f;

    public BranchingFractalDrawing(int x, int y, BranchingFractalDrawingConfig conf, long seed) {
        super(x, y);
        this.seed = seed;
        random = new SplittableRandom(seed);
        this.conf = conf;
    }

    @Override
    protected void onDraw(Graphics2D g) {
        random = new SplittableRandom(seed);
        recursiveDraw(g);
    }

    private void recursiveDraw(Graphics2D g) {
        R(conf.length, conf.stroke, 1.0f, conf.depth, g);
    }

    private void R(int length, float stroke, float opacity, int depth, Graphics2D g) {

        float depthFactor= Math.min(1f, ((conf.depth - depth) / (float) conf.depth) * (float) Math.E);
        //                      ^                         ^                                       ^
        //                      |                         |                                       |
        //                      |     [0,1] прогресс глубины (начинает с нуля)    магический множитель, чтобы сдвинуть увеличение
        //       ограничение магического множителя                                         ближе к началу процесса роста

        float baseCurve = random.nextFloat(conf.curveFactor.lBound(), conf.curveFactor.rBound());

        float curveFactor = conf.minCurveFactor * baseCurve + (1 - conf.minCurveFactor) * baseCurve * depthFactor;
        //                                  ^                                           ^
        //                                  |                                           |
        //                          часть от базового изгиба             остальное зависит от фактора глубины
        g.setColor(conf.colorLUT[(int) ((conf.colorLUT.length - 1) * opacity)]); //да тут 254 зато без Math.ceil();

        float bR = stroke * 0.5f;                              // bottomRadius
        float tR = stroke * BRANCH_TAPER_FACTOR * 0.5f;        // topRadius


        float bend1 = curveFactor * length;                    // зависят от длины ветки
        float bend2 = -curveFactor * length;

        float y1 = -0.33f * length;                            // две ординаты опорных точек
        float y2 = -0.66f * length;                            // находятся ровно на 1/3 и 2/3 от длины

        float r1 = bR * 0.5f + tR * 0.5f;                      // добавляем зависимость от ширины ветки
        float r2 = bR * 0.5f + tR * 0.5f;                      // через среднее арифметическое ширины бота и топа конусообразной формы ветки

        branchShape.moveTo(-bR, 0);                                                   // лево низ (прыжок)
        branchShape.quadTo(0, bR, bR, 0);                                        // право низ (выпукла вниз)
        branchShape.curveTo(bend1 + r1, y1, bend2 + r2, y2, tR, -length);        // право верх (s-образная)
        branchShape.quadTo(0, -1.5 * tR + length * -1, -tR, -length);            // лево верх (выпукла вверх)
        branchShape.curveTo(bend2 - r2, y2, bend1 - r1, y1, -bR, 0);         // лево низ (s-образная)
        branchShape.closePath();
        g.fill(branchShape);
        branchShape.reset();

        g.translate(0, -length);

        int branchesQuantity = conf.randomBranching ? Utils.weightedRandomInt(conf.branchingFactor, random) : conf.branching;

        // базовый случай и отрисовка декора на концах последних веток
        // работает также когда ширина ветки меньше базовой в float раз
        if (depth == 0 || branchesQuantity < 1 || (stroke / conf.stroke) < conf.cutoffStrokeFactor) {
            if (conf.branchItems != null && conf.branchingFactor != null) {
                // не рисуем айтем в зависимости от интенсивности
                if (random.nextFloat(0, 1f) < (1 - conf.branchItemsIntensityFactor)) {
                    g.translate(0, length);
                    return;
                }
                //рандомно выбираем предмет в зависимости от массива весов
                int index = Utils.weightedRandomInt(conf.branchItemsFactor, random);
                BranchItem bi = conf.branchItems[index];
                Interval<Float> BISizeFactor = conf.branchItemsSizeFactor.get(index);
                bi.setContext(random, stroke, opacity, BISizeFactor, conf.maxBranchItemSize); // безысходный каст
                bi.draw(g);
            }
            g.translate(0, length);

            return;
        }
        // делим первые две четверти окружности на сегменты в зависимости от branchesQuantity
        double segmentAngle = conf.angle * 2 / (branchesQuantity + 1);
        double currentAngle = -1 * conf.angle;
        //отрисовка по ветке на сегмент и рекурсивный вызов
        for (int i = 0; i < branchesQuantity; i++) {
            currentAngle += segmentAngle;
//            if (depth > conf.depth - 3 && random.nextDouble() < 0.3) {
//                R((int)(length * 0.2), stroke * 0.2f, opacity, depth - 6, g);
//            }
            double randomAngle = conf.forcedSpreading ? currentAngle : currentAngle * random.nextDouble(conf.angleFactor.lBound(), conf.angleFactor.rBound());
            int randomLength = (int) (length * random.nextDouble(conf.lengthFactor.lBound(), conf.lengthFactor.rBound()));
            float randomStroke = stroke * random.nextFloat(conf.strokeFactor.lBound(), conf.strokeFactor.rBound());
            float randomOpacity = opacity * random.nextFloat(conf.opacityFactor.lBound(), conf.opacityFactor.rBound());

            g.rotate(randomAngle);

            R(randomLength, randomStroke, randomOpacity, depth - 1, g);
            g.rotate(-1 * randomAngle);
        }
        g.translate(0, length);
    }
}