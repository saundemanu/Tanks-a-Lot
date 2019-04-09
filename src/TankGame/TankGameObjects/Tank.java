package TankGame.TankGameObjects;

import gameEngine.RenderingUtil.Camera;
import gameEngine.gameObjects.GameObject;
import gameEngine.gameObjects.Movable;
import gameEngine.gameObjects.ObjectManager;


import java.awt.*;
import java.awt.geom.AffineTransform;

public class Tank extends GameObject implements Movable {
    private int spawnX, spawnY;
    private Color color;
    private double ROTATION_SPEED = 5;
    private double MOVEMENT_SPEED = 4;
    private int MAX_LIVES;
    private int MAX_HEALTH;
    private int lives;
    private int health;
    private int ammo;
    private boolean alive = true;
    private Rectangle bounds;
    private int angle ;

    private boolean up;
    private boolean left;
    private boolean down;
    private boolean right;
    private boolean fire;

    private ObjectManager objectManager;

    public Tank(int x, int y, ObjectID id, ObjectManager objectManager, Camera camera, Color color){
        super(x, y, id);
        this.objectManager = objectManager;
        width = 32;
        height = 32;
        this.color = color;
        bounds = new Rectangle();
        spawnX = x;
        spawnY = y;
    }

    public void setMovement(boolean up, boolean down, boolean left, boolean right, boolean action){
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.fire = action;
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

            }if(fire){
                objectManager.addPlayerOneBullet(new Bullet((int)(x/2 + 1), (int)(y/2 + 1), vx, vy, getId()));
            }



        }
    }
    private boolean wallCollision;
    private boolean playerCollision;

    private void collision(){
        for(GameObject gameObject : objectManager.getObjectList()){
            if(getBounds().intersects(gameObject.getBounds()) && !gameObject.equals(this)){
                  if(gameObject instanceof IndestructableWall){
                      wallCollision = true;
                  }
                  if(gameObject instanceof Tank && !gameObject.equals(this)){
                      playerCollision = true;
                  }

            }
        } if(wallCollision || playerCollision){
            x += vx * -1;
            y += vy * -1;
            wallCollision = false;
            if(playerCollision){
                playerCollision = false;
            }
        }
    }


    @Override
    public Rectangle getBounds() {
        bounds.setBounds((int)x, (int)y, width, height);
        return bounds;
    }

    @Override
    public void update() {
        Movement();
        collision();

    }




    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(color);
        Rectangle rectangle = bounds;
        Rectangle small = new Rectangle((int)(x/2), (int)(y/2), 8, 16);
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), x  + height /2d, y + width /2d);
        Shape tf = transform.createTransformedShape(rectangle);
        AffineTransform old = g2d.getTransform();
        g2d.fill(tf);
        g2d.setTransform(old);
//        g2d.dispose();
    }
}
