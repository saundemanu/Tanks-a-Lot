package gameEngine.gameObjects.TankGameObjects.LevelAssets;

import gameEngine.Util.ObjectID;

import java.awt.*;

public class AmmoCrate extends Item {
    public AmmoCrate(int x, int y, ObjectID id) {
        super(x, y, id);
    }

    @Override
    public void drawImage(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }
}
