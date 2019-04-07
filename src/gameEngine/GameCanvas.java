package gameEngine;

import gameEngine.gameObjects.Camera;
import gameEngine.gameObjects.ObjectManager;

import java.awt.*;
import java.awt.image.BufferStrategy;

import static gameEngine.Game.DEFAULT_HEIGHT;
import static gameEngine.Game.DEFAULT_WIDTH;

public class GameCanvas  extends Canvas {
    private Camera camera;
    private ObjectManager objectManager;

    public GameCanvas(Camera camera, ObjectManager objectManager) {
        this.camera = camera;
        this.objectManager = objectManager;
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        //if bs null, create new triple-buffer
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        /************Rendering section***********/
        //rendering background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,DEFAULT_WIDTH/2, DEFAULT_HEIGHT);
        g2d.translate(-camera.getX(), -camera.getY());
        //objects rendered after background
        objectManager.render(g);
        g2d.translate(camera.getX(), camera.getY());

        /***************************************/
        g.dispose();
        bs.show();
    }

}
