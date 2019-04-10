package gameEngine.Util;

import gameEngine.gameObjects.GameObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankGameController extends KeyAdapter {
    //using an adapter over a listener prevents the empty keytyped method requirement
    private ObjectManager objectManager;

    public TankGameController(ObjectManager objectManager) {
        this.objectManager = objectManager;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (GameObject object : objectManager.getObjectList()) {
            if (object.getId() == ObjectID.PlayerOne) {
                if (key == KeyEvent.VK_W) objectManager.setPlayerOneUp(true);
                if (key == KeyEvent.VK_A) objectManager.setPlayerOneLeft(true);
                if (key == KeyEvent.VK_S) objectManager.setPlayerOneDown(true);
                if (key == KeyEvent.VK_D) objectManager.setPlayerOneRight(true);
                if (key == KeyEvent.VK_SPACE){objectManager.setPlayerOneAction(true);}

            }
            if (object.getId() == ObjectID.PlayerTwo) {
                if (key == KeyEvent.VK_SPACE) objectManager.setPlayerOneAction(true);
                if (key == KeyEvent.VK_UP) objectManager.setPlayerTwoUp(true);
                if (key == KeyEvent.VK_LEFT) objectManager.setPlayerTwoLeft(true);
                if (key == KeyEvent.VK_DOWN) objectManager.setPlayerTwoDown(true);
                if (key == KeyEvent.VK_RIGHT) objectManager.setPlayerTwoRight(true);
                if (key == KeyEvent.VK_ENTER) objectManager.setPlayerTwoAction(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (GameObject object : objectManager.getObjectList()) {
            if (object.getId() == ObjectID.PlayerOne) {
                if (key == KeyEvent.VK_W) objectManager.setPlayerOneUp(false);
                if (key == KeyEvent.VK_A) objectManager.setPlayerOneLeft(false);
                if (key == KeyEvent.VK_S) objectManager.setPlayerOneDown(false);
                if (key == KeyEvent.VK_D) objectManager.setPlayerOneRight(false);
                if (key == KeyEvent.VK_SPACE) objectManager.setPlayerOneAction(false);
            }
            if (object.getId() == ObjectID.PlayerTwo) {
                if (key == KeyEvent.VK_UP) objectManager.setPlayerTwoUp(false);
                if (key == KeyEvent.VK_LEFT) objectManager.setPlayerTwoLeft(false);
                if (key == KeyEvent.VK_DOWN) objectManager.setPlayerTwoDown(false);
                if (key == KeyEvent.VK_RIGHT) objectManager.setPlayerTwoRight(false);
                if (key == KeyEvent.VK_ENTER) objectManager.setPlayerTwoAction(false);
            }
        }
    }
}
