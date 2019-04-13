package gameEngine.gameObjects;

import java.awt.*;


public abstract class GameObject {
    protected double x, y;
    protected double vx = 0, vy = 0;
    protected int height, width;
    protected Rectangle bounds;
    private final ObjectID id;

    //constructor
    protected GameObject(int x, int y, ObjectID id) {
        this.x = x;
        this.y = y;
        this.id = id;
        bounds = new Rectangle();
    }

    public ObjectID getId() {
        return this.id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    //pass the graphic to the render to be drawn on
    public abstract void drawImage(Graphics g);

    //using rectangle-based collision boxes
    public abstract Rectangle getBounds();
}
