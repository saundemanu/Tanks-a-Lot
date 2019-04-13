package gameEngine.Util;

import gameEngine.gameObjects.ObjectID;
import gameEngine.gameObjects.TankGameObjects.LevelAssets.AmmoCrate;
import gameEngine.gameObjects.TankGameObjects.LevelAssets.DestructibleWall;
import gameEngine.gameObjects.TankGameObjects.LevelAssets.HealthBoost;
import gameEngine.gameObjects.TankGameObjects.LevelAssets.IndestructibleWall;
import gameEngine.gameObjects.TankGameObjects.PlayerAssets.Tank;
import gameEngine.gameObjects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class LevelLoader {
    private ObjectManager objectManager;
    private int scale;
    private ImageLoader imgLoad;
    private int width = 2048, height = 2048;

    public LevelLoader(ObjectManager objectManager, int scale) {
        imgLoad = new ImageLoader();
        this.objectManager = objectManager;
        this.scale = scale;
    }


    public void loadLevel(String path) {
        BufferedImage levelImage = imgLoad.loadImage(path);

        int r, g, b, currentPixel;
        //buffer
        List<GameObject> levelObjects = new LinkedList<>();
        this.width = levelImage.getWidth() * scale;
        this.height = levelImage.getHeight() * scale;
        for (int i = 0; i < levelImage.getWidth(); i++) {
            for (int j = 0; j < levelImage.getHeight(); j++) {
                currentPixel = levelImage.getRGB(i, j);
                //comparing bits to white value 255
                r = (currentPixel >> 16) & 0xff;
                g = (currentPixel >> 8) & 0xff;
                b = (currentPixel) & 0xff;

                if (r == 255 && g == 255 && b == 255) {
                    levelObjects.add(new IndestructibleWall(i * scale, j * scale, ObjectID.IndestructibleWall));
                } else if (r == 153 && g == 153 && b == 153) {
                    levelObjects.add(new DestructibleWall(i * scale, j * scale, ObjectID.DestructibleWall));
                } else if (r == 0 && g == 255 && b == 255) {
                    levelObjects.add(new AmmoCrate(i * scale, j * scale, ObjectID.AmmoItem));
                } else if (r == 0 && g == 255 && b == 0) {
                    levelObjects.add(new HealthBoost(i * scale, j * scale, ObjectID.HealthItem));
                } else if (r == 0 && g == 0 && b == 255) {
                    levelObjects.add(new Tank(i * scale, j * scale, ObjectID.PlayerOne, objectManager, Color.BLUE));
                } else if (r == 255 && g == 0 && b == 0) {
                    levelObjects.add(new Tank(i * scale, j * scale, ObjectID.PlayerTwo, objectManager, Color.RED));
                }


            }
        }
        objectManager.init(levelObjects);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
