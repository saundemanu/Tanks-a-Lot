package TankGame.TankGameObjects.LevelItems;

import gameEngine.Util.ObjectID;

import java.awt.*;

public class HealthBoost extends Item {

    public HealthBoost(int x, int y, ObjectID id) {
        super(x, y, id);
        stat = 10;
    }

    @Override
    public void drawImage(Graphics g) {
        if(isActive()){
            g.setColor(Color.gray);


        }
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
