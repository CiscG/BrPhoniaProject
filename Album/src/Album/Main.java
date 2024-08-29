package Album;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DBManager db = new DBManager();
        LoadImage loadImage = new LoadImage();
        Connection connection = db.connectionToDB("AlbumDB","postgres","123456");
        db.readData(connection,"test");
        loadImage.loadImage("asdasdasd");
        /*
        db.insertRow(connection, "test", "gasd","Brazil");
        db.updateName(connection, "test", "Gab", "Gabriel");
        db.searchByCategory(connection, "test", "Gab");
        db.deleteRowByName(connection, "test", "gasd");
        db.deleteRowByID(connection,"test", 1);
        db.deleteTable(connection, "test");
        */

    }
}