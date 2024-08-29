package Album;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        //ler nome de usuario
        String nameUser = "Gab";
        DBManager db = new DBManager();
        //ShowPhoto showPhoto = new ShowPhoto();
        BufferedImage photo;
        Connection connection = db.connectionToDB("AlbumDB","postgres","123456");
        db.readData(connection,nameUser);
        //photo = loadImage.loadImage("asdasdasd");

        db.insertRow(connection, nameUser, "gasd","Brazil");
        /*
        db.updateCategory(connection, nameUser, "Gab", "Gabriel");
        db.searchByCategory(connection, nameUser, "Gab");
        db.deleteRowByCategory(connection, nameUser, "gasd");
        db.deleteRowByImage(connection,nameUser, 1);
        db.deleteTable(connection, nameUser);
        */


    }
}