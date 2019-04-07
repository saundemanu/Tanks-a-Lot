package gameEngine.gameObjects;

import TankGame.TankGameObjects.ObjectID;

import java.awt.*;


public abstract class GameObject {
protected double x, y;
protected double vx = 0, vy  =0;
private ObjectID id;

//constructor
public  GameObject(int x, int y, ObjectID id){
this.x = x;
this.y=  y;
this.id = id;
}

    public ObjectID getId(){
    return this.id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    //update object state
    public abstract void update();
    //pass the graphic to the render to be drawn on
    public abstract void render(Graphics g);
    //using rectangle-based collision boxes
    public abstract Rectangle getBounds();
}
