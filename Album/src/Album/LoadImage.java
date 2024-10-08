package Album;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class LoadImage {
    byte[] imageBytes;
    BufferedImage photo;
    public static File OpenFileViaExplorer(){
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
                String fileName = selectedFile.getName();
                String extension = "";
                int i = fileName.lastIndexOf(".");
                if(i >= 0){
                    extension = fileName.substring(i+1);
                }
                if(selectedFile.length()/1048576 <= 300 || extension.equals("png") || extension.equals("jpg")){
                    if(!Desktop.isDesktopSupported()){
                        System.out.println("Not Supported");
                        return null;
                    }else{
                        Desktop desktop = Desktop.getDesktop();
                        //desktop.open(selectedFile);
                        return selectedFile;
                    }
                }
                else{
                    System.out.println("Arquivo grande demais");
                    return null;
                }

            }else if (result == JFileChooser.CANCEL_OPTION){
                System.out.println("Canceled");
                return null;
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return null;
    }
    public BufferedImage loadImage(File file){
        File pathPhoto = file;
        try {
            imageBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.out.println(e);
            photo = null;
        }
        try {
            photo = ImageIO.read(pathPhoto);
        }catch (Exception e){
            System.out.println(e);
            photo = null;
        }
        return photo;
    }
}
