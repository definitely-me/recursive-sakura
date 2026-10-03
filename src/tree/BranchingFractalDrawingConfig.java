package tree;

import core.Drawing;
import utils.Interval;
import utils.Utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class BranchingFractalDrawingConfig {

    int depth = 8;                          // макс глубина рекурсии
    int length = 80;                        // длина ствола (начальной ветки) в пикселях
    double angle = Math.toRadians(20);      // макс угол раскидки веток от центра в обе стороны
    float stroke = 2.0f;                    // толщина ствола в пикселях
    int branching = 3;                      // кол-во дочерних веток (если не рандомное)
    boolean randomBranching = false;        // true -> кол-во веток выбирается по branchingFactor весам
    boolean forcedSpreading = true;         // true -> ветки равномерно по углу, false -> с рандомным множителем
    float minCurveFactor = 0.5f;            // доля изгиба, которая не зависит от глубины
    float cutoffStrokeFactor = 0.07f;       // если толщина ветки < stroke * это поле -> обрезаю рекурсию досрочно

    Color branchColor = new Color(71, 14, 14);           // базовый цвет веток
    Color[] colorLUT = Utils.getOpacityLUT(branchColor); // LUT из 255 цветов с разной прозрачностью -> нет мучению куче при аллокации цветов, аллокации нет


    double[] branchingFactor = new double[] {0.3, 0.3, 0.3}; // веса для weighted random: индекс = кол-во веток, значение = вес

    BranchItem[] branchItems;                    // рисунки на концах веток
    double[] branchItemsFactor;                  // веса выбора каждого айтема из branchItems
    List<Interval<Float>> branchItemsSizeFactor; // диапазон рандома размера для каждого айтема
    float branchItemsIntensityFactor = 1.0f;     // вероятность отрисовки айтема на конце ветки [0..1]
    int maxBranchItemSize = 1;                   // верхний клэмп размера айтема в пикселях

    Interval<Float> strokeFactor = new Interval<>(1.0f, 1.0f);   // множитель толщины дочерней ветки
    Interval<Float> lengthFactor = new Interval<>(0.75f, 0.75f);  // множитель длины дочерней ветки
    Interval<Float> angleFactor = new Interval<>(1.0f, 1.0f);     // множитель угла раскидки
    Interval<Float> opacityFactor = new Interval<>(1.0f, 1.0f);   // множитель прозрачности с каждым уровнем
    Interval<Float> curveFactor = new Interval<>(0.0f, 0.0f);     // диапазон изгиба ветки (s-образная кривая)

    public BranchingFractalDrawingConfig() {}

    public BranchingFractalDrawingConfig setDepth(int depth) {
        this.depth = depth;
        return this;
    }

    public BranchingFractalDrawingConfig setLength(int length) {
        this.length = length;
        return this;
    }

    public BranchingFractalDrawingConfig setAngle(double angle) {
        this.angle = angle;
        return this;
    }

    public BranchingFractalDrawingConfig setStroke(float stroke) {
        this.stroke = stroke;
        return this;
    }

    public BranchingFractalDrawingConfig setBranching(int branching) {
        this.branching = branching;
        return this;
    }

    public BranchingFractalDrawingConfig setRandomBranching(boolean randomBranching) {
        this.randomBranching = randomBranching;
        return this;
    }

    public BranchingFractalDrawingConfig setStrokeFactor(float min, float max) {
        this.strokeFactor = new Interval<>(min, max);
        return this;
    }

    public BranchingFractalDrawingConfig setLengthFactor(float min, float max) {
        this.lengthFactor = new Interval<>(min, max);
        return this;
    }

    public BranchingFractalDrawingConfig setAngleFactor(float min, float max) {
        this.angleFactor = new Interval<>(min, max);
        return this;
    }

    public BranchingFractalDrawingConfig setOpacityFactor(float min, float max) {
        this.opacityFactor = new Interval<>(min, max);
        return this;
    }

    public BranchingFractalDrawingConfig setCurveFactor(float min, float max) {
        this.curveFactor = new Interval<>(min, max);
        return this;
    }

    public BranchingFractalDrawingConfig setBranchingFactor(double[] branchingFactor) {
        if (branchingFactor.length == 0) {
            throw new IllegalArgumentException("Tree must have at least one non-zero branch possibility");
        }
        this.branchingFactor = branchingFactor;
        return this;
    }

    public BranchingFractalDrawingConfig setForcedSpreading(boolean forcedSpreading) {
        this.forcedSpreading = forcedSpreading;
        return this;
    }

    public BranchingFractalDrawingConfig setMinCurveFactor(float minCurveFactor) {
        this.minCurveFactor = minCurveFactor;
        return this;
    }

    public BranchingFractalDrawingConfig setBranchItems(BranchItem[] branchItems, double[] branchItemsFactor, List<Interval<Float>> branchItemsSizeFactor) {
        if (branchItems.length != branchItemsFactor.length || branchItems.length != branchItemsSizeFactor.size()) {
            throw new IllegalArgumentException("make sure your branchItems.length == branchItemsFactor.length == branchItemsSizeFactor.size()");
        }
        this.branchItems = branchItems;
        this.branchItemsFactor = branchItemsFactor;
        this.branchItemsSizeFactor = branchItemsSizeFactor;

        return this;
    }

    public BranchingFractalDrawingConfig setBranchItemsIntensityFactor(float branchItemsIntensityFactor) {
        this.branchItemsIntensityFactor = branchItemsIntensityFactor;
        return this;
    }

    public BranchingFractalDrawingConfig setMaxBranchItemSize(int maxBranchItemSize) {
        this.maxBranchItemSize = maxBranchItemSize;
        return this;
    }

    public BranchingFractalDrawingConfig setCutoffStrokeFactor(float cutoffStrokeFactor) {
        this.cutoffStrokeFactor = cutoffStrokeFactor;
        return this;
    }

    public BranchingFractalDrawingConfig setBranchColor(Color branchColor) {
        this.branchColor = branchColor;
        this.colorLUT = Utils.getOpacityLUT(branchColor);
        return this;
    }

}