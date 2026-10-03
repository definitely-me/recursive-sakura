# Recursive Sakura 🌸
![Java](https://img.shields.io/badge/Java-17+-orange)

A fractal tree generator built with Java Swing. Uses recursive branching to draw tree-like structures with sakura flowers and leaves on branch tips.

![tree](/sakura.png)

## How it works

The program draws a tree by recursively splitting each branch into several child branches. Each branch is an S-shaped bezier curve with configurable curvature, length, thickness, and angle. At the tips of the final branches, sakura flowers and leaves are drawn as decorations.

Two trees are rendered on top of each other (back and front layers) to create depth.

## Configuration

All tree parameters are set through `BranchingFractalDrawingConfig`. Here's what each option controls:

| Parameter | Type | Description |
|---|---|---|
| `depth` | `int` | Max recursion depth |
| `length` | `int` | Trunk length in pixels |
| `angle` | `double` | Max spread angle from center (radians) |
| `stroke` | `float` | Trunk thickness in pixels |
| `branching` | `int` | Number of child branches (if not random) |
| `randomBranching` | `boolean` | Use weighted random for branch count |
| `forcedSpreading` | `boolean` | `true` = even angle distribution, `false` = random spread |
| `minCurveFactor` | `float` | Portion of curve that doesn't depend on depth |
| `cutoffStrokeFactor` | `float` | Stop recursion early if stroke gets too thin |
| `branchColor` | `Color` | Base branch color |

**Per-level random multipliers** (each is an `Interval<Float>` defining min/max range):

| Parameter | Description |
|---|---|
| `strokeFactor` | Child branch thickness multiplier |
| `lengthFactor` | Child branch length multiplier |
| `angleFactor` | Angle spread multiplier |
| `opacityFactor` | Opacity multiplier per level |
| `curveFactor` | Branch curvature range (S-shape) |

**Branch decorations:**

| Parameter | Type | Description |
|---|---|---|
| `branchItems` | `BranchItem[]` | Decoration types (flowers, leaves) |
| `branchItemsFactor` | `double[]` | Selection weights for each item |
| `branchItemsSizeFactor` | `List<Interval<Float>>` | Size range per item type |
| `branchItemsIntensityFactor` | `float` | Probability of drawing an item `[0..1]` |
| `maxBranchItemSize` | `int` | Upper clamp for item size in pixels |
| `branchingFactor` | `double[]` | Weights for weighted random branch count (index = count) |

## Project structure

    src/
    ├── Main.java                          — entry point
    ├── core/
    │   ├── Drawing.java                   — base class with transform save/restore
    │   └── DrawingFrame.java              — Swing window and render loop
    ├── tree/
    │   ├── BranchingFractalDrawing.java   — recursive fractal tree renderer
    │   ├── BranchingFractalDrawingConfig.java — all tree parameters
    │   ├── BranchItem.java                — base class for branch tip decorations
    │   ├── SakuraFlower.java              — flower decoration
    │   ├── SakuraLeaf.java                — leaf decoration
    │   └── TreeConfigurations.java        — preset configs (back/front tree)
    └── utils/
        ├── Interval.java                  — generic min/max range
        └── Utils.java                     — weighted random, color LUT
