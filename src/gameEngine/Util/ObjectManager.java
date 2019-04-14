package gameEngine.Util;

import gameEngine.gameObjects.ObjectID;
import gameEngine.gameObjects.TankGameObjects.LevelAssets.DestructibleWall;
import gameEngine.gameObjects.TankGameObjects.LevelAssets.Item;
import gameEngine.gameObjects.TankGameObjects.LevelAssets.Wall;
import gameEngine.gameObjects.TankGameObjects.PlayerAssets.Tank;

import gameEngine.gameObjects.GameObject;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;

public class ObjectManager {
    //Linkedlist over arraylist due to O(1) add  efficiency


    private LinkedList<Wall> wallList = new LinkedList<>();
    private LinkedList<Item> itemList = new LinkedList<>();
    private LinkedList<Tank> tankList = new LinkedList<>();
    private boolean respawning;
    private boolean playerOneDown;
    private boolean playerOneUp;
    private boolean playerOneLeft;
    private boolean playerOneRight;
    private boolean playerOneAction;
    private ObjectID winner;

    public LinkedList<Item> getItemList() {
        return itemList;
    }

    private boolean playerTwoDown;
    private boolean playerTwoUp;
    private boolean playerTwoLeft;
    private boolean playerTwoRight;
    private boolean playerTwoAction;


    void init(Collection<GameObject> list) {
        clear();
        for (GameObject obj : list) {
            if (obj instanceof Wall) {
                wallList.add((Wall) obj);
            }
            if (obj instanceof Tank) {
                tankList.add((Tank) obj);
            }
            if (obj instanceof Item) {
                itemList.add((Item) obj);
            }
        }
    }


    public void update() {

        for (Tank t : tankList) {
            if (t.getId() == ObjectID.PlayerOne) {
                t.setMovement(playerOneUp, playerOneDown, playerOneLeft, playerOneRight, playerOneAction);
            } else if (t.getId() == ObjectID.PlayerTwo) {
                t.setMovement(playerTwoUp, playerTwoDown, playerTwoLeft, playerTwoRight, playerTwoAction);
            }
            t.update();
        }
        for (Item i : itemList) {
            i.respawn();
        }

    }

    public LinkedList<Tank> getTankList() {
        return tankList;
    }

    public void respawn() {
        for (Tank t : tankList) {
            t.respawn();
        }
        for (Wall w : wallList) {
            if (w instanceof DestructibleWall) {
                ((DestructibleWall) w).repair();
            }
        }
        for (Item i : itemList) {
            i.respawn();
        }
    }


    public boolean isRespawning() {
        for (Tank t : tankList) respawning = !t.checkSpawnDelay();
        return respawning;
    }

    public boolean isGameOver() {
        for (Tank t : tankList) {
            if (t.isOutOfLives()) {
                for (Tank tank : tankList) {
                    if (!tank.isOutOfLives()) {
                        winner = tank.getId();
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public ObjectID getWinner() {
        return winner;
    }

    public void drawImages(Graphics g) {

        for (Wall w : wallList) {
            w.drawImage(g);
        }

        for (Item i : itemList) {
            i.drawImage(g);
        }
        for (Tank t : tankList) {
            t.drawImage(g);
        }

    }

    private void clear() {
        if (!itemList.isEmpty())
            itemList.clear();
        if (!wallList.isEmpty())
            wallList.clear();
        if (!tankList.isEmpty())
            tankList.clear();
    }


    public LinkedList<Wall> getWallList() {
        return wallList;
    }

     void setPlayerOneDown(boolean playerOneDown) {
        this.playerOneDown = playerOneDown;
    }

     void setPlayerOneUp(boolean playerOneUp) {
        this.playerOneUp = playerOneUp;
    }

     void setPlayerOneLeft(boolean playerOneLeft) {
        this.playerOneLeft = playerOneLeft;
    }

     void setPlayerOneRight(boolean playerOneRight) {
        this.playerOneRight = playerOneRight;
    }

     void setPlayerOneAction(boolean playerOneAction) {
        this.playerOneAction = playerOneAction;
    }

     void setPlayerTwoDown(boolean playerTwoDown) {
        this.playerTwoDown = playerTwoDown;
    }

     void setPlayerTwoUp(boolean playerTwoUp) {
        this.playerTwoUp = playerTwoUp;
    }

     void setPlayerTwoLeft(boolean playerTwoLeft) {
        this.playerTwoLeft = playerTwoLeft;
    }

     void setPlayerTwoRight(boolean playerTwoRight) {
        this.playerTwoRight = playerTwoRight;
    }

     void setPlayerTwoAction(boolean playerTwoAction) {
        this.playerTwoAction = playerTwoAction;
    }


}
