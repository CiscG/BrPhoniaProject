package Album;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoadImage {
    BufferedImage photo;
    public void loadImage(String path){
        try {
            photo = ImageIO.read(DBManager.class.getResource(path));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
