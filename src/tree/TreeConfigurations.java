package tree;

import core.Drawing;
import utils.Interval;

import java.awt.*;
import java.util.List;

public final class TreeConfigurations {
    public static final BranchingFractalDrawingConfig configBack = new BranchingFractalDrawingConfig()
            .setDepth(10)
            .setLength(200)
            .setStroke(40.0f)
            .setAngle(Math.toRadians(60))
            .setAngleFactor(0.75f, 1.15f)
            .setLengthFactor(0.6f, 0.9f)
            .setStrokeFactor(0.65f, 0.7f)
            .setRandomBranching(true)
            .setBranchingFactor(new double[] {0.5, 1, 5, 5, 2, 1})
            .setForcedSpreading(false)
            .setOpacityFactor(0.85f, 1f)
            .setCurveFactor(-0.2f, 0.2f)
            .setCutoffStrokeFactor(0.01f)
            .setBranchItems(new BranchItem[] {new SakuraLeaf(), new SakuraFlower()}, new double[] {5, 5}, List.of(
                    new Interval<>(0.7f, 1.2f),
                    new Interval<>(0.7f, 1.5f)
            ))
            .setBranchItemsIntensityFactor(0.7f)
            .setMaxBranchItemSize(3)
            .setBranchColor( new Color(71, 31, 44))
            .setMinCurveFactor(0.5f);

    public static final BranchingFractalDrawingConfig configFront = new BranchingFractalDrawingConfig()
            .setDepth(13)
            .setLength(170)
            .setStroke(30.0f)
            .setAngle(Math.toRadians(60))
            .setAngleFactor(0.75f, 1.35f)
            .setLengthFactor(0.55f, 0.9f)
            .setStrokeFactor(0.65f, 0.7f)
            .setRandomBranching(true)
            .setBranchingFactor(new double[] {0.5, 1, 5, 5, 2, 1})
            .setForcedSpreading(false)
            .setOpacityFactor(0.9f, 1f)
            .setCurveFactor(-0.2f, 0.2f)
            .setCutoffStrokeFactor(0.1f)
            .setBranchItems(new BranchItem[] {new SakuraLeaf(), new SakuraFlower()}, new double[] {5, 5}, List.of(
                    new Interval<>(0.7f, 1.2f),
                    new Interval<>(0.7f, 1.5f)
            ))
            .setBranchItemsIntensityFactor(0.7f)
            .setMaxBranchItemSize(3)
            .setBranchColor( new Color(71, 31, 44))
            .setMinCurveFactor(0.5f);

}
