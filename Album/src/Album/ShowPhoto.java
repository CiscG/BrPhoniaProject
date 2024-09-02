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
    JLabel label = new JLabel();
    Timer timer = new Timer();
    int counter = 5;
    List<Categories> categories;
    Iterator<Categories> list = null;
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
                if (counter > 0) {
                    System.out.println(counter + "segundos");
                    counter--;
                } else {
                    System.out.println("Next photo");
                    ImageIcon icon = new ImageIcon(list.next().photo);
                    label.setSize(600, 400);
                    Image image = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(image));
                    updateImage();
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
        list = this.categories.listIterator();
        ImageIcon icon = new ImageIcon(categories.getFirst().photo);
        Image image = icon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        updateImage();
    }
    public void updateImage(){
        timer.schedule(task, 0);
    }
}
