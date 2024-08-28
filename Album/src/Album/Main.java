package Album;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DBManager db = new DBManager();
        Connection connection;
        connection = db.connection_to_db("AlbumDB","postgres","123456");
        db.createTable(connection, "test");
        db.insert_photo(connection, "test", "Gab","Brazil");
    }
}