package gameEngine.gameObjects.TankGameObjects.PlayerAssets.UI;

import java.awt.*;

abstract class StatusBar {

    private int MAX_STATUS;
    int status;

    StatusBar(int status, int MAX_STATUS) {
        this.status = status;
        this.MAX_STATUS = MAX_STATUS;
    }

    public abstract void update(int x, int y, int status);

    public abstract void drawImage(Graphics g);

     int getMAX_STATUS() {
        return this.MAX_STATUS;
    }

}
