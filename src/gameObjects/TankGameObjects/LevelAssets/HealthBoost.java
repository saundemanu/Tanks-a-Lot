package gameObjects.TankGameObjects.LevelAssets;

import gameObjects.ObjectID;

import java.awt.*;

public class HealthBoost extends Item {

    public HealthBoost(int x, int y, ObjectID id) {
        super(x, y, id);
        bounds.setBounds(x, y, width, height);
        stat = 30;
    }

    @Override
    public void drawImage(Graphics g) {
        if(isActive()){
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.GREEN);
            g2d.fill(bounds);
            g2d.setColor(Color.lightGray);
            g2d.draw(bounds);

        }
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }
}
