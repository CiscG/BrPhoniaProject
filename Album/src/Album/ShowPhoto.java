package Album;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ShowPhoto extends JPanel {
    BufferedImage photo;
    public ShowPhoto(){
        setSize(600,400);
        setVisible(true);

    }
    public void loadImage(List<Categories> category){
        //Image image = photo.

    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(photo, 0, 0, 600, 400, this);
    }
    /*public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setSize(600, 400);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new ShowPhoto());
                /*
                Set timer for change photos
                updateUI();
            }
        });
    }*/
}
