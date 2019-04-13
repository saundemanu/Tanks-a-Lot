package gameEngine.gameObjects.TankGameObjects.PlayerAssets.UI;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class LifeCount extends StatusBar {
    Ellipse2D oval[];
    public LifeCount(int status, int MAX_STATUS) {
        super(status, MAX_STATUS);
        oval = new Ellipse2D[MAX_STATUS];
        for(int i = 0; i < getMAX_STATUS(); i++ ){
            oval[i] = new Ellipse2D.Double();
        }

    }

    @Override
    public void update(int x, int y, int status) {
        for(int i = 0; i < getMAX_STATUS(); i++){
            oval[i].setFrame(x - (15*i), y + 72, 10, 10);

        }
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.PINK);
        for(int i = 0; i < status; i++){
            g2d.draw(oval[i]);
        }

    }

    @Override
    protected int getMAX_STATUS() {
        return super.getMAX_STATUS();
    }
}
