package Album;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class LoadImage {
    BufferedImage photo;
    public static boolean OpenFileViaExplorer(){
        try{
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png","jpg");
            fileChooser.setFileFilter(filter);
            fileChooser.setCurrentDirectory(new File("."));
            int result = fileChooser.showOpenDialog(null);
            System.out.println("Result" + result);
            if(result == JFileChooser.APPROVE_OPTION){
                File selectedFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println("Filepath" + selectedFile);

                if(!Desktop.isDesktopSupported()){
                    System.out.println("Not Supported");
                    return false;
                }else{
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(selectedFile);
                    return true;
                }
            }else if (result == JFileChooser.CANCEL_OPTION){
                System.out.println("Canceled");
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return false;
    }
    public BufferedImage loadImage(String path){
        File pathPhoto = new File(path);
        try {
            photo = ImageIO.read(pathPhoto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return photo;
    }
}
