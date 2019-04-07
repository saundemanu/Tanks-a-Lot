package gameEngine.gameObjects;

import TankGame.TankGameObjects.ObjectID;

import java.awt.*;

public class testBox extends GameObject{
    public testBox(int x, int y, ObjectID id){
        super(x, y, id);
        vx = 1;
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void update() {
    x += vx;
    y += vy;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)getX(), (int)getY(), 32, 32);
    }
}
