package gameEngine.gameObjects.TankGameObjects.LevelAssets;

import gameEngine.Util.ObjectID;

import java.awt.*;

public class IndestructibleWall extends Wall {
    public IndestructibleWall(int x, int y, ObjectID id) {
        super(x, y, id);
    }



    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.WHITE);
        g2d.fill(this.bounds);
    }


}
