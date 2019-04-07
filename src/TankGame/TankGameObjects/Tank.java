package TankGame.TankGameObjects;

import gameEngine.gameObjects.GameObject;
import gameEngine.gameObjects.ObjectManager;
import org.w3c.dom.css.Rect;


import java.awt.*;
import java.awt.geom.AffineTransform;

public class Tank extends GameObject {

    private double ROTATION_SPEED = 5;
    private double MOVEMENT_SPEED = 3;
    private int MAX_LIVES;
    private int MAX_HEALTH;
    private int lives;
    private int health;
    private boolean alive = true;

    private int angle ;
    private int sizeH = 32, sizeV = 32;

    private boolean up;
    private boolean left;
    private boolean down;
    private boolean right;

    public Tank(int x, int y, ObjectID id){
        super(x, y, id);
    }

    public void setMovement(boolean up, boolean down, boolean left, boolean right){
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public void Movement() {
        if (alive) {
            if(up && !down){
                vx = (MOVEMENT_SPEED * Math.cos(Math.toRadians(angle)));
                vy = (MOVEMENT_SPEED * Math.sin(Math.toRadians(angle)));
                x += vx;
                y += vy;
            } if(down && !up){
                vx = (MOVEMENT_SPEED * Math.cos(Math.toRadians(angle)));
                vy = (MOVEMENT_SPEED * Math.sin(Math.toRadians(angle)));
                x -= vx;
                y -= vy;
            }
            if(left && !right){

                angle -= ROTATION_SPEED;
            }if(right && !left){
                angle += ROTATION_SPEED;

            }

        }
    }

    public void fire(){

    }


    @Override
    public Rectangle getBounds() {
        return new Rectangle(sizeH, sizeV);
    }

    @Override
    public void update() {
        Movement();

    }




    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(Color.BLUE);
        Rectangle rectangle = new Rectangle((int)x, (int)y, sizeH, sizeV);
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), x  + sizeH/2d, y + sizeV/2d);
        Shape tf = transform.createTransformedShape(rectangle);
        AffineTransform old = g2d.getTransform();
        g2d.fill(tf);
        g2d.setTransform(old);
        g2d.dispose();
    }
}
