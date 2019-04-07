package gameEngine.gameObjects;

import TankGame.TankGameObjects.ObjectID;

public class Camera {
    private float x, y;
    private int viewWidth, viewHeight;
    public Camera(float x, float y, int viewWidth, int viewHeight) {
        this.x = x;
        this.y = y;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
    }

    public void update(GameObject obj){
        x += ((obj.getX() - x ) - viewWidth/4f) * 0.05f;
        y += ((obj.getY() - y ) - viewHeight/2f) * 0.05f;
        if(obj.getId() == ObjectID.PlayerOne) {
            if (x <= 0) x = 0;
            if (x >= 1550) x = 1550 ;
            if (y <= 0) y = 0;
            if (y >= viewHeight) y = viewHeight + 256;
            System.out.println("x: "  +  x + " y: "  + y + "\tID:" + obj.getId());
        }
        if(obj.getId() == ObjectID.PlayerTwo){

        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
