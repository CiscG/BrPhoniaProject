package Album;

public class Main {
    public static void main(String[] args) {
        DBManager db = new DBManager();
        db.connection_to_db("AlbumDB","postgres","123456");
    }
}