package gameEngine.gameObjects.TankGameObjects.PlayerAssets.UI;

import java.awt.*;

public class HealthBar extends StatusBar {
    private  Rectangle base;
    private  Rectangle top;

    public HealthBar(int health, int maxHealth) {
        super(health, maxHealth);
        base = new Rectangle(100, 5);
        top = new Rectangle();
    }

    public void update(int x, int y, int health) {
        base.setLocation(x - 32, y + 64);
        status = health;
    }

    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fill(base);
        g2d.setColor(Color.GREEN);
        top.setBounds(base.x, base.y, base.width * status / this.getMAX_STATUS(), base.height);
        g2d.fill(top);
        if (status <= getMAX_STATUS() / 3) {
            g2d.drawString("Low Health!", base.x, base.y + 32);
        }
    }
}
