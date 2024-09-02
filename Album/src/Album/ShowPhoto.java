package Album;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.*;
import java.util.Timer;

public class ShowPhoto extends JPanel {
    BufferedImage photo;
    Timer timer = new Timer();
    int counter = 5;
    List<Categories> categories;
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
                if (counter > 0) {
                    System.out.println(counter + "segundos");
                    counter--;
                } else {
                    System.out.println("Next photo");
                    timer.cancel();
                }
            }
        };
    public ShowPhoto(){
        setSize(600,400);
        setVisible(true);

    }
    public void loadImage(List<Categories> category){
        this.categories = category;
        ImageIcon icon = new ImageIcon(categories.getFirst().photo);
        Image image = icon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        //label.setIcon(new ImageIcon(image));
    }
    public void updateImage(){

    }
}
