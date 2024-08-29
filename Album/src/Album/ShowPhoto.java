package Album;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ShowPhoto extends JPanel {
    BufferedImage image;

    public ShowPhoto(){
        setSize(600,400);
        setVisible(true);

    }

    public void loadImage(BufferedImage photo){
        image = photo;
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(image, 0, 0, 600, 400, this);
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
