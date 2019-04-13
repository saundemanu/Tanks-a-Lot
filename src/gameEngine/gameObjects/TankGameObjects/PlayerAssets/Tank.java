package gameEngine.gameObjects.TankGameObjects.PlayerAssets;

import gameEngine.gameObjects.TankGameObjects.LevelAssets.Item;
import gameEngine.gameObjects.TankGameObjects.LevelAssets.AmmoCrate;
import gameEngine.gameObjects.TankGameObjects.LevelAssets.DestructibleWall;
import gameEngine.gameObjects.TankGameObjects.LevelAssets.Wall;
import gameEngine.gameObjects.TankGameObjects.LevelAssets.HealthBoost;

import gameEngine.gameObjects.TankGameObjects.PlayerAssets.UI.AmmoBar;
import gameEngine.gameObjects.TankGameObjects.PlayerAssets.UI.HealthBar;
import gameEngine.gameObjects.TankGameObjects.PlayerAssets.UI.LifeCount;
import gameEngine.gameObjects.ObjectID;
import gameEngine.gameObjects.GameObject;
import gameEngine.Util.ObjectManager;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

public class Tank extends GameObject {
    //Gameplay modifiers/stats
    private int SPAWN_X, SPAWN_Y;
    private final double ROTATION_SPEED = 5;
    private final double MOVEMENT_SPEED = 6;
    private final int SPAWN_DELAY = 3000; //spawn delay in ms, locks controls;
    private final int MAX_LIVES = 3;
    private final int MAX_HEALTH = 30;
    private final int MAX_AMMO = 10;
    private final int FIRING_DELAY = 1000;
    //Tank UI
    private HealthBar healthbar;
    private LifeCount lifeCount;
    private AmmoBar ammoBar;

    //stat trackers
    private boolean outOfLives;
    private int lives;
    private int health;
    private int ammo;


    //list of active bullets
    private LinkedList<Bullet> clip = new LinkedList();
    //list of dead bullets, prevents concurrent Modification exception
    private LinkedList<Bullet> clipBuffer = new LinkedList<>();
    private boolean alive = true;
    private long deathTime = 0;
    private long firedTime = 0;

    //tank drawing
    private Rectangle base;
    private Rectangle barrel;
    private Rectangle cap;
    private Color drawColor;
    private Color playerColor;
    private Color outline = Color.BLACK;
    private Stroke stroke = new BasicStroke(4);
    private int angle;
//controls
    private boolean up;
    private boolean left;
    private boolean down;
    private boolean right;
    private boolean fire;

    private boolean collision;

    private ObjectManager objectManager;

    public Tank(int x, int y, ObjectID id, ObjectManager objectManager, Color color) {
        super(x, y, id);
        this.objectManager = objectManager;
        width = 40;
        height = 40;
        playerColor = color;
        drawColor = playerColor;

        base = new Rectangle();
        barrel = new Rectangle();
        cap = new Rectangle();
        SPAWN_X = x;
        SPAWN_Y = y;
        lives = MAX_LIVES;
        health = MAX_HEALTH;
        ammo = MAX_AMMO;
        healthbar = new HealthBar(health, MAX_HEALTH);
        lifeCount = new LifeCount(lives, MAX_LIVES);
        ammoBar = new AmmoBar(ammo, MAX_AMMO);
    }

    public void setMovement(boolean up, boolean down, boolean left, boolean right, boolean action) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.fire = action;
    }

    private void Movement() {
        alive = (checkSpawnDelay() && lives > 0);
        if (alive) {
            if (up && !down) {
                vx = (MOVEMENT_SPEED * Math.cos(Math.toRadians(angle)));
                vy = (MOVEMENT_SPEED * Math.sin(Math.toRadians(angle)));
                x += vx;
                y += vy;
            }
            if (down && !up) {

                vx = (MOVEMENT_SPEED * Math.cos(Math.toRadians(angle)));
                vy = (MOVEMENT_SPEED * Math.sin(Math.toRadians(angle)));
                x -= vx;
                y -= vy;
            }
            if (left && !right) {
                angle -= ROTATION_SPEED;
            }
            if (right && !left) {
                angle += ROTATION_SPEED;
            }

            if (fire) {
                if (System.currentTimeMillis() - firedTime >= FIRING_DELAY) {
                    Bullet bullet = new Bullet((int) x + width/2 - 8, (int) y + height / 2 - height / 8, this.getId());
                    bullet.fire(angle);
                    if (ammo > 0) {
                        clip.add(bullet);
                        ammo--;
                        firedTime = System.currentTimeMillis();
                    }
                }

            }

        }
    }

    private void checkCollision() {
        for (Wall wall : objectManager.getWallList()) {
            if (getBounds().intersects(wall.getBounds()) && !wall.isDestructed()) {
                collision = true;
            }
            for (Bullet b : clip) {
                if (b.getBounds().intersects(wall.getBounds())) {
                    if (wall instanceof DestructibleWall) {
                        ((DestructibleWall) wall).destruct();
                    } else if (b.isActive())
                        b.collision(wall);
                    else clipBuffer.add(b);
                }
            }
        }
        for (Item i : objectManager.getItemList()) {
            if (i.getBounds().intersects(getBounds()) && i.isActive()) {
                if (i instanceof AmmoCrate) {
                    for(Tank t: objectManager.getTankList())
                        t.ammo = (i.getStat());
                } else if (i instanceof HealthBoost) {
                    for(Tank t: objectManager.getTankList())
                        t.health = (i.getStat());
                }
                i.used();
            }
        }
        for (Tank t : objectManager.getTankList()) {
            if (t.equals(this)) continue;
            if (t.getBounds().intersects(getBounds())) collision = true;
            for (Bullet b : clip) {
                if (t.getBounds().intersects(b.getBounds()) && b.getOwner() != t.getId()) {
                    for(Tank tank : objectManager.getTankList()) {
                       if(t.getId() == tank.getId())
                        tank.damage(b.getDamage());
                    }
                    clipBuffer.add(b);
                }
            }
        }

        clip.removeAll(clipBuffer);
        clipBuffer.clear();

        if (collision) {
            handleCollision();
        }
    }

    public void handleCollision() {
        if (up) {
            x += vx * -1;
            y += vy * -1;
        }
        if (down) {
            x -= vx * -1;
            y -= vy * -1;
        }
        collision = false;
    }

    @Override
    public Rectangle getBounds() {
        bounds.setBounds((int) x, (int) y, width + width / 8, height + height / 8);
        return bounds;
    }

    public void update() {
        Movement();
        for (Bullet b : clip) {
            b.update();
        }
        checkCollision();
        healthbar.update((int) x, (int) y, health);
        lifeCount.update((int) x, (int) y, lives);
        ammoBar.update((int) x, (int) y, ammo);
    }

    private void damage(int amount) {
        if (alive) {
            health -= amount;
            System.out.println(getId() + " lost " + amount + "health");
            if (health <= 0) {
                killed();
            }
        }
    }

    //removes life and checks life count
    private void killed() {
        lives--;
        alive = false;
        System.out.println(getId() + " died");
        if (lives > 0) {
            //respawn
            objectManager.respawn();

        } else {
            System.out.println(getId() + " is out of lives");
            outOfLives = true;
        }
    }

    public boolean isOutOfLives() {
        return outOfLives;
    }

    //checks difference in death time, return true if ready to respawn;
    public boolean checkSpawnDelay() {
        return (System.currentTimeMillis() - deathTime) >= SPAWN_DELAY;
    }


    public void respawn() {
        x = SPAWN_X;
        y = SPAWN_Y;
        health = MAX_HEALTH;
        ammo = MAX_AMMO;
        deathTime = System.currentTimeMillis();
    }

    @Override
    public void drawImage(Graphics g) {
        if (alive) {
            Graphics2D g2d = (Graphics2D) g.create();
            for (Bullet b : clip) {
                b.drawImage(g2d);
            }
            base.setBounds((int) x, (int) y, width, height);
            barrel.setBounds((int) x + width - width / 4, (int) y + height / 2 - height / 8, 3 * width / 4, height / 4);
            cap.setBounds((int) x + width / 4, (int) y + height / 4, width / 2, height / 2);
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(angle), x + height / 2d, y + width / 2d);
            Shape bottom = transform.createTransformedShape(base);
            Shape mid = transform.createTransformedShape(barrel);
            Shape top = transform.createTransformedShape(cap);
            AffineTransform old = g2d.getTransform();
            g2d.setStroke(stroke);
            g2d.setColor(outline);
            g2d.draw(bottom);
            g2d.setColor(drawColor);
            g2d.fill(bottom);
            g2d.setColor(outline);
            g2d.draw(mid);
            g2d.setColor(drawColor);
            g2d.fill(mid);
            g2d.setColor(outline);
            g2d.draw(top);
            g2d.setColor(drawColor);
            g2d.fill(top);
            g2d.setTransform(old);
            healthbar.drawImage(g2d);
            lifeCount.drawImage(g2d);
            ammoBar.drawImage(g2d);
        }
    }
}
