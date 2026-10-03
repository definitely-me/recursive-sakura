package core;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class Drawing {
    private int posX = 0;
    private int posY = 0;
    private double scale = 1;
    private float opacity = 1.0f;
    private double rotation = 0;

    public Drawing(int x, int y) {
        posX = x;
        posY = y;
    }

    public Drawing() {
    }

    public int getPosX() {
        return posX;
    }

    public Drawing setPosX(int posX) {
        this.posX = posX;
        return this;
    }

    public int getPosY() {
        return posY;
    }

    public Drawing setPosY(int posY) {
        this.posY = posY;
        return this;
    }

    public double getScale() {
        return scale;
    }

    public Drawing setScale(double scale) {
        this.scale = scale;
        return this;
    }

    public Drawing setOpacity(float opacity) {
        this.opacity = opacity;
        return this;
    }

    public float getOpacity() {
        return opacity;
    }

    public double getRotation() {
        return rotation;
    }

    public Drawing setRotation(double rotation) {
        this.rotation = rotation;
        return this;
    }

    public void draw(Graphics2D g) {
        AffineTransform oldTransform = g.getTransform();
        Composite oldComposite = g.getComposite();

        g.translate(posX, posY);
        g.rotate(rotation);
        g.scale(scale, scale);

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

        onDraw(g);

        g.setComposite(oldComposite);
        g.setTransform(oldTransform);
    }

    protected abstract void onDraw(Graphics2D g);
}
