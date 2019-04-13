package gameEngine.RenderingUtil;


import gameEngine.gameObjects.GameObject;

public class Camera {
    private float x, y;
    private final int viewWidth, viewHeight;

    public Camera(float x, float y, int viewWidth, int viewHeight) {
        this.x = x;
        this.y = y;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
    }

    public void update(GameObject obj) {
        x += ((obj.getX() - x) - viewWidth / 4f) * 0.05f;
        y += ((obj.getY() - y) - viewHeight / 2f) * 0.05f;
        if (x <= 0) x = 0;
        if (x >= 1550) x = 1550;
        if (y <= 0) y = 0;
        if (y >= 1325) y = 1325;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
