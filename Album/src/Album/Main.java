package Album;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        //ler nome de usuario
        String nameUser = "Gab";
        DBManager db = new DBManager();
        ShowPhoto showPhoto = new ShowPhoto();
        BufferedImage photo;
        Connection connection = db.connectionToDB("AlbumDB","postgres","123456");
        db.readData(connection,nameUser);
        //photo = loadImage.loadImage("asdasdasd");
        /*
        db.insertRow(connection, "test", "gasd","Brazil");
        db.updateCategory(connection, "test", "Gab", "Gabriel");
        db.searchByCategory(connection, "test", "Gab");
        db.deleteRowByCategory(connection, "test", "gasd");
        db.deleteRowByImage(connection,"test", 1);
        db.deleteTable(connection, "test");
        */


    }
}