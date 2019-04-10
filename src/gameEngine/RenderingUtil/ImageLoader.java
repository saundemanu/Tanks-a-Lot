package gameEngine.RenderingUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageLoader {
    private BufferedImage img;

    public BufferedImage loadImage(String path){
        try{
        img = ImageIO.read(getClass().getResource(path));
        }catch(Exception e){
            e.printStackTrace();
        }
        return img;

    }

}
