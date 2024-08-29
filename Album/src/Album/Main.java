package Album;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DBManager db = new DBManager();
        Connection connection = db.connection_to_db("AlbumDB","postgres","123456");;
        //db.createTable(connection, "test");
        //db.insertRow(connection, "test", "Gab","Brazil");
        db.readData(connection,"test");
    }
}