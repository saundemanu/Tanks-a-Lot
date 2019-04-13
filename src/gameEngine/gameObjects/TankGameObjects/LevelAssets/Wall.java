package gameEngine.gameObjects.TankGameObjects.LevelAssets;

import gameEngine.gameObjects.ObjectID;
import gameEngine.gameObjects.GameObject;

import java.awt.*;

public abstract class Wall extends GameObject {


    protected boolean destructed;


    protected Wall(int x, int y, ObjectID id) {
        super(x, y, id);
        width = 32;
        height = 32;
        bounds = new Rectangle(x, y, width, height);
        destructed = false;
    }

    public boolean isDestructed() {
        return destructed;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

}
