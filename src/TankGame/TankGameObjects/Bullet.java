package TankGame.TankGameObjects;

import gameEngine.gameObjects.GameObject;
import gameEngine.gameObjects.Movable;

import java.awt.*;

public class Bullet extends GameObject implements Movable  {

    ObjectID owner;

     Bullet(int x, int y, double vx, double vy, ObjectID owner) {
        super(x, y, ObjectID.Bullet);
        height = 16;
        width = 16;
        this.owner = owner;
        this.vx = vx;
        this.vy = vy;
    }

    @Override
    public void update() {
        x += vx;
        y += vx;
    }

    @Override
    public void drawImage(Graphics g) {
    g.fillOval((int)x, (int)y, 12, 12);
    }

    @Override
    public Rectangle getBounds() {
         //collision bounds
        return new Rectangle((int)x, (int)y, height, width);
    }
}
