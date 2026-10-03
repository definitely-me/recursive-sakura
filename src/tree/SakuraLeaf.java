package tree;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class SakuraLeaf extends BranchItem {

    private static final Color LEAF_COLOR = new Color(220, 80, 110);
    public SakuraLeaf(int x, int y) {super(x, y);}

    public SakuraLeaf() {super();}

    @Override
    protected void drawItem(Graphics2D g) {
        float r = Math.max(8, Math.min(stroke * 2.0f, maxBranchItemSize));

        int leavesCount = (random != null) ? random.nextInt(1, 4) : 1;

        for (int i = 0; i < leavesCount; i++) {

            float bend = 0;
            if (random != null) {
                g.rotate(random.nextDouble() * Math.PI * 2);
                
                float localScale = random.nextFloat(sizeFactor.lBound(), sizeFactor.rBound());
                setScale(localScale);

                bend = (float) (random.nextDouble() - 0.5) * r * 1.5f;
            }
            // также я навайбкодил листок красивый
            Path2D.Float leaf = new Path2D.Float();
            leaf.moveTo(0, 0);
            leaf.curveTo(-r * 0.8f, -r * 1.5f, 
                         -r * 0.8f + bend, -r * 2.5f, 
                         bend, -r * 3.0f);
            leaf.lineTo(bend, -r * 2.7f);
            leaf.lineTo(bend + r * 0.2f, -r * 3.0f);
            leaf.curveTo(r * 0.8f + bend, -r * 2.5f, 
                         r * 0.8f, -r * 1.5f, 
                         0, 0);
            leaf.closePath();

            g.setColor(LEAF_COLOR);
            g.fill(leaf);

        }
    }
}
