package TankGame.TankGameObjects;

import gameEngine.Util.ObjectID;
import gameEngine.gameObjects.GameObject;

import java.awt.*;

public abstract class Wall extends GameObject {

    private Rectangle bounds = new Rectangle();

    public Wall(int x, int y, ObjectID id) {
        super(x, y, id);
        width = 32;
        height = 32;
    }


    @Override
    public void drawImage(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect((int)x, (int)y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        bounds.setBounds((int)x, (int)y, width, height);
        return bounds;
    }

}
