package gameEngine.gameObjects;

import TankGame.TankGameObjects.ObjectID;
import TankGame.TankGameObjects.Tank;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;

public class ObjectManager {
    //Linkedlist over arraylist due to O(1) add  efficiency
LinkedList<GameObject> masterObjectList = new LinkedList<>();
LinkedList<Integer> inputList = new LinkedList<>();

boolean playerOneDown;
boolean playerOneUp;
boolean playerOneLeft;
boolean playerOneRight;
boolean playerOneAction;

boolean playerTwoDown;
boolean playerTwoUp;
boolean playerTwoLeft;
boolean playerTwoRight;
boolean playerTwoAction;
    Tank player;
    Tank player2;

public void update(){
    //iterate through all objects in the game and update them
    for(GameObject obj : masterObjectList){

        if(obj.getId() == ObjectID.PlayerOne ){
            ((Tank) obj).setMovement(playerOneUp, playerOneDown, playerOneLeft, playerOneRight);
        }
        if(obj.getId() == ObjectID.PlayerTwo){
            ((Tank) obj).setMovement(playerTwoUp, playerTwoDown, playerTwoLeft, playerTwoRight);
        }
        obj.update();
    }
}
public void render(Graphics g) {
    for (GameObject obj : masterObjectList) {
       obj.render(g);
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
