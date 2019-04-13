package TankGame.TankGameObjects.LevelItems;


import gameEngine.Util.ObjectID;
import gameEngine.gameObjects.GameObject;

import java.awt.*;


abstract class Item extends GameObject {
    protected int stat;
    protected double modifier;
    private boolean active;
    private final long SPAWN_DELAY = 10000;
    private long SpawnTimer;
    protected int width, height;



    public Item(int x, int y, ObjectID id) {
        super(x, y, id);
    }

    int getStat(){
        return this.stat;
    }

    boolean isActive(){
        return this.active;
    }

    public void respawn(){
        if(!active && System.currentTimeMillis() - SpawnTimer >= SPAWN_DELAY){
            active = true;
        }
    }

}
