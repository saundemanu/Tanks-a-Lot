import gameEngine.Util.LevelLoader;
import gameObjects.ObjectID;
import gameObjects.TankGameObjects.PlayerAssets.Tank;
import gameEngine.RenderingUtil.Camera;
import gameEngine.RenderingUtil.GameWindow;
import gameEngine.Util.TankGameController;
import gameEngine.Util.ObjectManager;


import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;


class Game extends JPanel implements Runnable {
    //uid verifies sender and receiver of serialized object for serializable loaded class
//    https://howtodoinjava.com/java/serialization/serialversionuid/
    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_WIDTH = 1000;
    private static final int DEFAULT_HEIGHT = DEFAULT_WIDTH / 12 * 9;
    private final String NAME = "Tanks A Lot";
    private long timer;

    private Thread thread;
    private boolean running = false;
    private final ObjectManager objectManager = new ObjectManager();

    private int gamestate;

    private Camera p1Camera = new Camera(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    private Camera p2Camera = new Camera(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    private final LevelLoader levelLoader;

    //game constructor;
    private Game() {
        this.setLayout(new BorderLayout());
        new GameWindow(DEFAULT_WIDTH, DEFAULT_HEIGHT, NAME, this);
        this.addKeyListener(new TankGameController(objectManager));
        this.requestFocus();
        levelLoader = new LevelLoader(objectManager, 32);
        levelLoader.loadLevel("/Tanks_Level1.png");
        gamestate = -2;
    }


    public static void main(String[] args) {
        new Game();
    }


    @Override
    public void run() {
        //Based on Minecraft game loop by Notch, chosen for consistent update/gameplay regardless of cpu
        long current;
        double elapsed = 0;
        double target = 60.0;
        //One second in nano seconds/target refresh rate in seconds
        double ns = 1000000000 / target;
        long last = System.nanoTime();
        //thread-safe ms
        long timer = System.currentTimeMillis();
        //store frame amount rendered per second
        int FPS = 0;
        int up = 0;
        while (running) {
            //fetch current time in loop
            current = System.nanoTime();
            //calculate time since last iteration
            elapsed += (current - last) / ns;
            //set time for next iteration
            last = current;
            while (elapsed >= 1) {
                //updates game state regardless of rendering
                update();
                up++;
                elapsed--;
                //fixes out of window clicking error
                if (!this.hasFocus()) this.requestFocus();
            }
            //check if game state changes during last update
            if (running) {
                repaint();
            }
            //increment rendered frames
            FPS++;
            //calculating frame per second (1000ms = 1s)
            //Prints out current FPS rate and resets for next second
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + FPS + "\tUpdates: " + up + "\tState: " + gamestate);
                FPS = 0;
                up = 0;
            }

        }
        stop();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        running = true;
    }


    private void stop() {

        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }


    private void update() {
        objectManager.update();
        for (Tank t : objectManager.getTankList()) {
            if (t.getId() == ObjectID.PlayerOne)
                p1Camera.update(t);
            else if (t.getId() == ObjectID.PlayerTwo)
                p2Camera.update(t);
        }

        if (objectManager.isGameOver()) {
            //if level two over, game over
            if (gamestate > 2) {
                gamestate = -1;
            } else {
                //if level one over, load level 2
                levelLoader.loadLevel("/Tanks_Level2.png");
                gamestate = 2;
            }
            //wait for level 2;
        } else if (gamestate == 2) {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //start level 2
            gamestate = 3;
            timer = System.currentTimeMillis();
            //respawning
        } else if (objectManager.isRespawning()) {
            gamestate = 0;
            //if game started, start timer
        } else if (gamestate == -2) {
            gamestate = 1;
            timer = System.currentTimeMillis();
        } else if (gamestate == 0) {
            gamestate = 1;
        }

        //Key:
        //-2 is game start;
        //-1 is Game End
        //0 is respawning
        //1 is Map 1 Playing
        //2 is load Map 2
        //3 is Map 2 playing
    }

    //paint demonstrated in class, tested at ~4 million renders/second
    @Override
    public void paintComponent(Graphics g) {
        BufferedImage p1screen;
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage world = (BufferedImage) createImage(2048, 2048);
        Graphics2D bufferOne = world.createGraphics();
        super.paintComponent(g2d);
        if (gamestate == 0) {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            Stroke temp = g2d.getStroke();
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.white);

            g2d.drawString("Respawning...",
                    DEFAULT_WIDTH / 2 - 64, DEFAULT_HEIGHT / 2);

            g2d.setStroke(temp);
        } else if (gamestate == 2) {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            Stroke temp = g2d.getStroke();
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.white);

            g2d.drawString(objectManager.getWinner()
                            + " won Level 1 in "
                            + (System.currentTimeMillis() - timer) / 1000
                            + " seconds Loading Level 2",
                    DEFAULT_WIDTH / 2 - 72, DEFAULT_HEIGHT / 2);

            g2d.setStroke(temp);
        } else if (gamestate == -1) {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            Stroke temp = g2d.getStroke();
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.white);

            g2d.drawString(objectManager.getWinner()
                            + " won Level 1 in "
                            + (System.currentTimeMillis() - timer) / 1000
                            + "Game Over",
                    DEFAULT_WIDTH / 2 - 64, DEFAULT_HEIGHT / 2);

            g2d.setStroke(temp);
            running = false;
        } else {
            bufferOne.setColor(Color.black);
            bufferOne.fillRect(0, 0, 2048, 2048);
            objectManager.drawImages(bufferOne);
            //method renders overall smoother but camera transitions lose float precision
            //player 1
            p1screen = world.getSubimage((int) p1Camera.getX(), (int) p1Camera.getY(), getWidth() / 2, getHeight());
            g2d.drawImage(p1screen, 0, 0, null);
            //player 2
            p1screen = world.getSubimage((int) p2Camera.getX(), (int) p2Camera.getY(), getWidth() / 2, getHeight());
            g2d.drawImage(p1screen, DEFAULT_WIDTH / 2, 0, null);
            //minimap
            g2d.drawLine(DEFAULT_WIDTH / 2, 0, DEFAULT_WIDTH / 2, DEFAULT_HEIGHT);
            g2d.scale(0.1, 0.1);
            g2d.drawImage(world, (DEFAULT_WIDTH) * 5 - getWidth(), DEFAULT_HEIGHT + getHeight() * 6, null);
            //timer
            g2d.scale(10, 10);
            g2d.setColor(Color.CYAN);
            g2d.fillRect(DEFAULT_WIDTH / 2 - 74, 50, 134, 20);
            g2d.setColor(Color.gray);
            g2d.fillRect(DEFAULT_WIDTH / 2 - 72, 52, 128, 16);
            g2d.setColor(Color.white);
            g2d.drawString("Time: " + (System.currentTimeMillis() - timer) / 1000 + ": " +
                    (System.currentTimeMillis() - timer) % 1000, DEFAULT_WIDTH / 2 - 64, 64);
        }
        g2d.dispose();
    }


}