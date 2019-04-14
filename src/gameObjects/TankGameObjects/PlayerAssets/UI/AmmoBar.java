package gameObjects.TankGameObjects.PlayerAssets.UI;

import java.awt.*;

public class AmmoBar extends StatusBar {
    private  Rectangle base;
    private  Rectangle top;

    public AmmoBar(int ammo, int maxAmmo) {
        super(ammo, maxAmmo);
        base = new Rectangle(10, 72);
        top = new Rectangle();
    }

    public void update(int x, int y, int ammo) {
        base.setLocation(x - 48, y - 32);
        status = ammo;
    }

    public void drawImage(Graphics g) {
        if (status != 0) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.MAGENTA);
            g2d.fill(base);
            g2d.setColor(Color.DARK_GRAY);
            top.setBounds(base.x, base.y, base.width, base.height * (getMAX_STATUS() - status) / getMAX_STATUS());
            g2d.fill(top);

            if (status <= getMAX_STATUS() / 4) {
                g2d.drawString("Low ammo!", base.x, base.y - 16);
            }
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.white);
            g2d.drawString("Out of Ammo!", base.x, base.y);

        }

    }
}
