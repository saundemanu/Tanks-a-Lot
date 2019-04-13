package gameEngine.Util;

import TankGame.TankGameObjects.PlayerAssets.Tank;
import TankGame.TankGameObjects.Wall;
import com.sun.jdi.connect.spi.TransportService;
import gameEngine.gameObjects.GameObject;
import gameEngine.gameObjects.Movable;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ObjectManager {
    //Linkedlist over arraylist due to O(1) add  efficiency
    private LinkedList<GameObject> masterObjectList = new LinkedList<>();
    private  LinkedList<GameObject> buffer = new LinkedList<>();

    private LinkedList<Tank> tankList = new LinkedList<>();
    private LinkedList<Wall> wallList = new LinkedList<>();

    private boolean playerOneDown;
    private boolean playerOneUp;
    private boolean playerOneLeft;
    private boolean playerOneRight;
    private boolean playerOneAction;


    private boolean playerTwoDown;
    private boolean playerTwoUp;
    private boolean playerTwoLeft;
    private boolean playerTwoRight;
    private boolean playerTwoAction;



public void update(){
    if(!buffer.isEmpty()){
        for(GameObject obj: buffer){
            masterObjectList.add(obj);
            buffer.remove(obj);
        }
    }
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
    buffer.add(obj);
    }

public void addObjectList(Collection<GameObject> objList){
    masterObjectList.addAll(objList);
}

public LinkedList<GameObject> getObjectList(){
    return this.masterObjectList;
}

public void addTankList(Collection<Tank> tankCollection){
    this.tankList.addAll(tankCollection);
}
public void addWallList(Collection<Wall> wallCollection){
        this.wallList.addAll(wallCollection);
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
