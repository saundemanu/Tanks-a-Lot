package gameEngine.gameObjects.TankGameObjects.LevelAssets;

import gameEngine.Util.ObjectID;

import java.awt.*;

public class AmmoCrate extends Item {
    public AmmoCrate(int x, int y, ObjectID id) {
        super(x, y, id);
        bounds.setBounds(x, y, width, height);
        stat = 10;
    }

    @Override
    public void drawImage(Graphics g) {
        if(isActive()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.MAGENTA);
            g2d.fill(bounds);
        }
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }
}
