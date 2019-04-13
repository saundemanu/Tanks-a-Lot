package gameEngine.gameObjects.TankGameObjects.LevelAssets;


import gameEngine.gameObjects.ObjectID;
import gameEngine.gameObjects.GameObject;


public abstract class Item extends GameObject {
    protected int stat;
    private boolean active;
    private final long SPAWN_DELAY = 15000;
    private long SpawnTimer;
    protected int width = 32, height = 32;


    protected Item(int x, int y, ObjectID id) {
        super(x, y, id);
        active = true;
    }

    public int getStat() {
        return this.stat;
    }

    public boolean isActive() {
        return this.active;
    }

    public void respawn() {
        if (!active && System.currentTimeMillis() - SpawnTimer >= SPAWN_DELAY) {
            active = true;
        }
    }

    public void used() {
        SpawnTimer = System.currentTimeMillis();
        active = false;
    }

}
