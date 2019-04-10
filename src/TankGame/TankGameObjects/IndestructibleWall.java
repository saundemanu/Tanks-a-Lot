package TankGame.TankGameObjects;

import gameEngine.Util.ObjectID;

public class IndestructibleWall extends Wall {
    public IndestructibleWall(int x, int y, ObjectID id) {
        super(x, y, id);
    }

    private boolean destructed = false;

    public boolean isDestructed() {
        return destructed;
    }
}
