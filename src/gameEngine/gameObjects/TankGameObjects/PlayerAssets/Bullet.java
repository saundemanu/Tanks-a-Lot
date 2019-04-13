package gameEngine.gameObjects.TankGameObjects.PlayerAssets;

import gameEngine.gameObjects.TankGameObjects.LevelAssets.DestructibleWall;
import gameEngine.Util.ObjectID;
import gameEngine.gameObjects.GameObject;

import java.awt.*;

public class Bullet extends GameObject  {

    private ObjectID owner;
    private int TRAVEL_SPEED = 20;

    private Rectangle bounds;
    private int damage = 10;
    private int MAX_COLLISIONS = 10;
    private int rebounds;
    private boolean active;

    Bullet(int x, int y, ObjectID owner) {
        super(x, y, ObjectID.Bullet);
        height = 16;
        width = 16;
        this.owner = owner;
        bounds = new Rectangle();
        active = true;
    }


    public void update() {
        x += vx;
        y += vy;

        }

    void fire(int angle){
        vx = (TRAVEL_SPEED * Math.cos(Math.toRadians(angle)));
        vy = (TRAVEL_SPEED * Math.sin(Math.toRadians(angle)));
    }

    @Override
    public void drawImage(Graphics g) {
         g.setColor(Color.GREEN);
         g.fillOval((int)x, (int)y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        bounds.setBounds((int)x, (int)y, width, height);
        return bounds;
    }

    ObjectID getOwner() {
        return owner;
    }

    int getDamage() {
        active = false;
        return damage;
    }

    public boolean isActive() {
        return active;
    }

    public void collision(GameObject obj){
        if(obj instanceof DestructibleWall) rebounds +=2;
        if(rebounds < MAX_COLLISIONS && active) {
            double objBottom = obj.getY() + obj.getHeight();
            double objRight = obj.getX() + obj.getWidth();
            double bulBottom = getY() + getHeight();
            double bulRight = getX() + getWidth();

            double bottom = objBottom - getY();
            double top = bulBottom - obj.getY();
            double left = bulRight - obj.getX();
            double right = objRight - getX();

            if (top < bottom && top < left && top < right) {
                vy *= -1;
            }
            else if (bottom < top && bottom < left && bottom < right) {
                vy *= -1;
            }
            else if (left < right && left < top && left < bottom){
                vx *= -1;
            }
            else if (right < left && right < top && right < bottom) {
                vx *= -1;
        }
            rebounds++;
        }else active = false;
    }
}