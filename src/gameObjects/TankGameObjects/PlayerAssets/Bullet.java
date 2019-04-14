package gameObjects.TankGameObjects.PlayerAssets;

import gameObjects.TankGameObjects.LevelAssets.DestructibleWall;
import gameObjects.ObjectID;
import gameObjects.GameObject;

import java.awt.*;

public class Bullet extends GameObject {

    private ObjectID owner;
    private final int TRAVEL_SPEED = 20;
    private final int damage = 10;
    private final int MAX_COLLISIONS = 10;

    private int rebounds;
    private boolean active;

    Bullet(int x, int y, ObjectID owner) {
        super(x, y, ObjectID.Bullet);
        height = 16;
        width = 16;
        this.owner = owner;
        active = true;
    }


    void update() {
        x += vx;
        y += vy;

    }

    void fire(int angle) {
        vx = (TRAVEL_SPEED * Math.cos(Math.toRadians(angle)));
        vy = (TRAVEL_SPEED * Math.sin(Math.toRadians(angle)));
    }

    @Override
    public void drawImage(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval((int) x, (int) y, width, height);
        g.setColor(Color.lightGray);
        g.drawOval((int) x, (int) y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        bounds.setBounds((int) x, (int) y, width, height);
        return bounds;
    }

    ObjectID getOwner() {
        return owner;
    }

    int getDamage() {
        active = false;
        return damage;
    }

    boolean isActive() {
        return active;
    }

    void collision(GameObject obj) {
        if (obj instanceof DestructibleWall) rebounds += 3;
        if (rebounds < MAX_COLLISIONS && active) {
            //detecting collision sides for reflection
            double objBottom = obj.getY() + obj.getHeight();
            double objRight = obj.getX() + obj.getWidth();
            double bulBottom = getY() + getHeight();
            double bulRight = getX() + getWidth();

            double bottom = objBottom - getY();
            double top = bulBottom - obj.getY();
            double left = bulRight - obj.getX();
            double right = objRight - getX();
            //top collision
            if (top < bottom && top < left && top < right) {
                vy *= -.9;
                vx *= .8;
            }
            //bottom collision
            else if (bottom < top && bottom < left && bottom < right) {
                vy *= -.9;
                vx *= .8;
            }
            //left collision
            else if (left < right && left < top && left < bottom) {
                vx *= -.9;
                vy *= .8;
            }
            //right collision
            else if (right < left && right < top && right < bottom) {
                vx *= -.9;
                vy *= .8;
            } else {
                vx*= -.9;
                vy*= -.9;
            }
            rebounds++;
        } else active = false;
    }
}
