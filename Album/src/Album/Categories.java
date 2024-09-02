package Album;
import java.awt.image.BufferedImage;
public class Categories{
    public int id;
    public String category;
    public BufferedImage photo;

    public String getCategory()
    {
        return this.category;
    }
    public int getId(){
        return this.id;
    }
    public BufferedImage getPhoto()
    {
        return this.photo;
    }
}
