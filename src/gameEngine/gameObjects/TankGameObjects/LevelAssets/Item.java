package gameEngine.gameObjects.TankGameObjects.LevelAssets;


import gameEngine.Util.ObjectID;
import gameEngine.gameObjects.GameObject;


abstract class Item extends GameObject {
    protected int stat;
    protected double modifier;
    private boolean active;
    private final long SPAWN_DELAY = 10000;
    private long SpawnTimer;
    protected int width, height;



    protected Item(int x, int y, ObjectID id) {
        super(x, y, id);
    }

    public int getStat(){
        return this.stat;
    }

    public boolean isActive(){
        return this.active;
    }

    public void respawn(){
        if(!active && System.currentTimeMillis() - SpawnTimer >= SPAWN_DELAY){
            active = true;
        }
    }

}
