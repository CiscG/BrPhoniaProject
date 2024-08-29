package Album;


import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ShowPhoto extends JPanel {
    public ShowPhoto(){
        setSize(600, 400);
        setVisible(true);
    }


    public void paintComponent(Graphics graphics, BufferedImage photo){
        super.paintComponent(graphics);
        graphics.drawImage(photo, 0, 0, 600, 400,this);
    }
}
