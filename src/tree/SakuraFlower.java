package tree;

import java.awt.*;

public class SakuraFlower extends BranchItem {

    private static final Color PETAL_COLOR = new Color(255, 183, 197);
    private static final Color CENTER_COLOR = new Color(220, 80, 110);
    public SakuraFlower(int x, int y) {super(x, y);}

    public SakuraFlower() {super();}

    @Override
    protected void drawItem(Graphics2D g) {
        int r = Math.max(7, Math.min((int) (stroke * 1.6f), maxBranchItemSize));

        if (random != null) {
            g.rotate(random.nextDouble() * Math.PI);
            setScale(random.nextFloat(sizeFactor.lBound(), sizeFactor.rBound()));
        }


        g.setColor(PETAL_COLOR);
        // рисуем 5 лепестков по кругу
        for (int i = 0; i < 5; i++) {
            g.rotate(Math.toRadians(72)); // 360 / 5  = 72
            java.awt.geom.Path2D.Float petal = new java.awt.geom.Path2D.Float();
            petal.moveTo(0, 0);
            // навайбкодил цветок ибо больно очень
            petal.curveTo(-r * 0.7f, -r * 1.2f, -r * 0.7f, -r * 2.3f, -r * 0.2f, -r * 2.6f);
            petal.lineTo(0, -r * 2.2f);
            petal.lineTo(r * 0.2f, -r * 2.6f);
            petal.curveTo(r * 0.7f, -r * 2.3f, r * 0.7f, -r * 1.2f, 0, 0);
            petal.closePath();
            g.fill(petal);
        }
        g.setColor(CENTER_COLOR);
        g.fillOval((int)(-r * 0.5f), (int)(-r * 0.5f), (int)(r * 1.0f), (int)(r * 1.0f));

    }
}
