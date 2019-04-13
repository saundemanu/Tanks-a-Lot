package TankGame.TankGameObjects;


import gameEngine.Util.ObjectID;
import gameEngine.gameObjects.GameObject;

import java.awt.*;

public abstract class Item extends GameObject {
    double buff;
    double nerf;

    public Item(int x, int y, ObjectID id) {
        super(x, y, id);
    }

    @Override
    public void drawImage(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
