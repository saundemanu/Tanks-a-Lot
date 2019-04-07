package gameEngine;

import TankGame.TankGameObjects.ObjectID;
import TankGame.TankGameObjects.Tank;
import TankGame.TankGameObjects.Wall;
import gameEngine.gameObjects.Camera;
import gameEngine.gameObjects.GameObject;
import gameEngine.gameObjects.ObjectManager;


import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;


public class Game implements Runnable {
//uid verifies sender and receiver of serialized object for serializable loaded class
//    https://howtodoinjava.com/java/serialization/serialversionuid/
private static final long serialVersionUID = 1L;

    private GameCanvas playerOneCanvas;
    private GameCanvas playerTwoCanvas;

    public static final int DEFAULT_WIDTH = 1000;
    public static final int DEFAULT_HEIGHT = DEFAULT_WIDTH / 12 * 9;
    private final String NAME = "Tanks A Lot";

    private Thread thread;
    private boolean running = false;
    ObjectManager objectManager = new ObjectManager();
    private BufferedImage level;
    private Camera p1Camera = new Camera(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    private Camera p2Camera = new Camera(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);



    //game constructor;
    public Game(){
        playerOneCanvas = new GameCanvas(p1Camera, objectManager);
        playerTwoCanvas = new GameCanvas(p2Camera, objectManager);
        GameWindow window = new GameWindow(DEFAULT_WIDTH, DEFAULT_HEIGHT, NAME,  playerOneCanvas, playerTwoCanvas, this);
        window.getFrame().addKeyListener(new InputHandler(objectManager));
        window.getFrame().requestFocus();
        ImageLoader imgLoader = new ImageLoader();
        level = imgLoader.loadImage("/Tanks_Level1.png");
//        p2Camera = new Camera(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        loadLevel(level);

    }


    public static void main(String args[]){
        new Game();
    }


    @Override
    public  void run() {
        //Based on Minecraft game loop by Notch, chosen for consistent update/gameplay regardless of cpu
        start();
        long current;
        double elapsed = 0;
        double target = 60.0;
        //One second in nano seconds/target refresh rate
        double ns = 1000000000 / target ;
        long last = System.nanoTime();
        //thread-safe ms
        long timer = System.currentTimeMillis();
        //store frame amount rendered per second
        int FPS = 0;
        int up = 0;
        while(running){
            //fetch current time in loop
            current = System.nanoTime();
            //calculate time since last iteration
            elapsed += (current - last) / ns;
            //set time for next iteration
            last = current;
            while(elapsed >= 1){
                //updates game state regardless of rendering
                update();
                up++;
                elapsed--;
            }
            //check if game state changes during last update
            if(running)
            render();
            //increment rendered frames
            FPS++;
            //calculating frame per second (1000ms = 1s)
            //Prints out current FPS rate and resets for next second
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + FPS + "\tUpdates: " + up);
                FPS = 0;
                up = 0;
            }

      }
        stop();
    }

    public void start(){
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
        running = true;
    }


    private void stop(){
    try{
        thread.join();
        running = false;
    }catch(Exception e){
        e.printStackTrace();
    }

    }

    private void loadLevel(BufferedImage image){
        int lvlw = image.getWidth();
        int lvlh = image.getHeight();
        int r,g,b, currentPixel;
        List<GameObject> levelObjects = new LinkedList<>();
        for(int i = 0; i < lvlw; i++){
            for(int j = 0; j < lvlh; j++ ) {
                currentPixel = image.getRGB(i, j);
                //comparing bits to white value 255
                r = (currentPixel >> 16) & 0xff;
                 g = (currentPixel >> 8) & 0xff;
                 b = (currentPixel) & 0xff;

                if(r == 255 && g == 255 && b == 255){
                    levelObjects.add(new Wall(i*32, j*32, ObjectID.Wall));
                }
                if(r == 0 && g == 0 && b == 255) {
                    //give time to load map, fixes concurrentModificationException;
                    levelObjects.add(new Tank(i*32, j*32, ObjectID.PlayerOne));
                }
                if(r == 255 && g == 0 && b == 0) {
                    //give time to load map, fixes concurrentModificationException;
                    levelObjects.add(new Tank(i*32, j*32, ObjectID.PlayerTwo));

                }
            }
        }
        objectManager.addObjectList(levelObjects);
    }

    private void update(){
        objectManager.update();
        for(GameObject obj: objectManager.getObjectList()){
            if(obj.getId() == ObjectID.PlayerOne){
               p1Camera.update(obj);
            }
            if(obj.getId() == ObjectID.PlayerTwo){
                p2Camera.update(obj);
            }
        }
    }

    private void render(){

        playerOneCanvas.render();
        playerTwoCanvas.render();
//        BufferStrategy bs = this.getBufferStrategy();
//        //if bs null, create new triple-buffer
//        if(bs == null){
//            this.createBufferStrategy(3);
//            return;
//        }
//        Graphics g = bs.getDrawGraphics();
//        Graphics2D g2d = (Graphics2D) g;
//        /************Rendering section***********/
//        //rendering background
//        g.setColor(Color.BLACK);
//        g.fillRect(0,0,DEFAULT_WIDTH, DEFAULT_HEIGHT);
//        g2d.translate(-p1Camera.getX(), -p1Camera.getY());
//        //objects rendered after background
//        objectManager.render(g);
//        g2d.translate(p1Camera.getX(), p1Camera.getY());
//
//        /***************************************/
//        g.dispose();
//        bs.show();
    }


}