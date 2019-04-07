package TankGame.TankGameObjects;

import gameEngine.gameObjects.GameObject;

import java.awt.*;

public class Wall extends GameObject {

    public Wall(int x, int y, ObjectID id) {
        super(x, y, id);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect((int)x, (int)y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }
}
