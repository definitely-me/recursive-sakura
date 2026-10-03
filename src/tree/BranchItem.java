package tree;

import core.Drawing;
import utils.Interval;

import java.awt.Graphics2D;
import java.util.SplittableRandom;

public abstract class BranchItem extends Drawing {

    protected SplittableRandom random;
    protected float stroke = 10.0f;
    protected Interval<Float> sizeFactor = new Interval<>(0.5f, 1.3f);
    protected int maxBranchItemSize = 1;

    public BranchItem(int x, int y) {super(x, y);}

    public BranchItem() {super();}

    protected void setContext(SplittableRandom random, float stroke, float opacity, Interval<Float> sizeFactor, int maxBranchItemSize) {
        this.random = random;
        this.stroke = stroke;
        setOpacity(opacity);
        this.sizeFactor = sizeFactor;
        this.maxBranchItemSize = maxBranchItemSize;
    }

    @Override
    protected final void onDraw(Graphics2D g) {
        drawItem(g);
    }

    protected abstract void drawItem(Graphics2D g);
}
