package TankGame.TankGameObjects;

import gameEngine.Util.ObjectID;
import gameEngine.gameObjects.GameObject;
import gameEngine.gameObjects.Movable;

import java.awt.*;

public class Bullet extends GameObject implements Movable {

    ObjectID owner;

     Bullet(int x, int y,  ObjectID owner) {
        super(x, y, ObjectID.Bullet);
        height = 8;
        width = 8;
        this.owner = owner;

    }

    @Override
    public void update() {
        x += vx;
        y += vx;
    }
    public void fire(double vx, double vy){
        this.vx = vx;
        this.vy = vy;
    }

    @Override
    public void drawImage(Graphics g) {
    g.fillOval((int)x, (int)y, 12, 12);
    }

    @Override
    public Rectangle getBounds() {
         //collision bounds
        return new Rectangle((int)x, (int)y, width, height);
    }
}
