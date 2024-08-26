package Album;
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;
import java.awt.image.BufferedImage;


public class Main {
    public static void main(String[] args) {
        Desktop d = null;
        File file = new File(System.getenv("programfiles"));

        /*if(Desktop.isDesktopSupported()){
            d = Desktop.getDesktop();
        }
            try{
                d.open(file);
            }
            catch (IOException e){

            }*/
    }

}