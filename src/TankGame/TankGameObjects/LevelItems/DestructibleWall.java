package TankGame.TankGameObjects.LevelItems;

import gameEngine.Util.ObjectID;

import java.awt.*;

public class DestructibleWall extends Wall{
    public DestructibleWall(int x, int y, ObjectID id) {
        super(x, y, id);
    }

    public void destruct(){
        destructed = true;
    }

    @Override
    public void drawImage(Graphics g) {
    if(!destructed) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.GRAY);
        g2d.fill(this.bounds);
    }
    }
}
