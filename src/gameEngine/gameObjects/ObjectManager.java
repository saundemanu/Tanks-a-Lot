package gameEngine.gameObjects;

import TankGame.TankGameObjects.Bullet;
import TankGame.TankGameObjects.ObjectID;
import TankGame.TankGameObjects.Tank;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;

public class ObjectManager {
    //Linkedlist over arraylist due to O(1) add  efficiency
    private LinkedList<GameObject> masterObjectList = new LinkedList<>();

    private boolean playerOneDown;
    private boolean playerOneUp;
    private boolean playerOneLeft;
    private boolean playerOneRight;
    private boolean playerOneAction;
    private LinkedList<Bullet> playerOneBullets = new LinkedList<>();


    private boolean playerTwoDown;
    private boolean playerTwoUp;
    private boolean playerTwoLeft;
    private boolean playerTwoRight;
    private boolean playerTwoAction;
    private LinkedList<Bullet> playerTwoBullets = new LinkedList<>();



public void update(){
    for(GameObject obj : masterObjectList){

        if(obj.getId() == ObjectID.PlayerOne ){
            ((Tank) obj).setMovement(playerOneUp, playerOneDown, playerOneLeft, playerOneRight, playerOneAction);
        }    //iterate through all objects in the game and update them

        if(obj.getId() == ObjectID.PlayerTwo){
            ((Tank) obj).setMovement(playerTwoUp, playerTwoDown, playerTwoLeft, playerTwoRight, playerTwoAction);
        }
        if(obj instanceof Movable) ((Movable) obj).update();

    }
}
public void drawImages(Graphics g) {
    for (GameObject obj : masterObjectList) {
       obj.drawImage(g);
    }
}

public void addObject(GameObject obj){
    masterObjectList.add(obj);
    }
public void removeObject(GameObject obj){
    masterObjectList.remove(obj);
}
public void addObjectList(Collection<GameObject> objList){
    masterObjectList.addAll(objList);
}

public LinkedList<GameObject> getObjectList(){
    return this.masterObjectList;
}

    public void addPlayerOneBullet(Bullet bullet){
    playerOneBullets.add(bullet);
    }

    public void setPlayerOneDown(boolean playerOneDown) {
        this.playerOneDown = playerOneDown;
    }

    public void setPlayerOneUp(boolean playerOneUp) {
        this.playerOneUp = playerOneUp;
    }

    public void setPlayerOneLeft(boolean playerOneLeft) {
        this.playerOneLeft = playerOneLeft;
    }

    public void setPlayerOneRight(boolean playerOneRight) {
        this.playerOneRight = playerOneRight;
    }

    public void setPlayerOneAction(boolean playerOneAction) {
        this.playerOneAction = playerOneAction;
    }

    public void setPlayerTwoDown(boolean playerTwoDown) {
        this.playerTwoDown = playerTwoDown;
    }

    public void setPlayerTwoUp(boolean playerTwoUp) {
        this.playerTwoUp = playerTwoUp;
    }

    public void setPlayerTwoLeft(boolean playerTwoLeft) {
        this.playerTwoLeft = playerTwoLeft;
    }

    public void setPlayerTwoRight(boolean playerTwoRight) {
        this.playerTwoRight = playerTwoRight;
    }

    public void setPlayerTwoAction(boolean playerTwoAction) {
        this.playerTwoAction = playerTwoAction;
    }


}
