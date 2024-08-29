package Album;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBManager {
    public Connection connection_to_db(String dbname, String user, String pwrd) {
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pwrd);
            if(connection != null) {
                System.out.println("Connection Established");
            }else{
                System.out.println("Connection Failed");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }
    public void createTable(Connection connection, String table_name){
        Statement statement;
        try {
            String query = "create Table " + table_name + "(employID SERIAL, name varchar(200), address varchar(200), primary key(employID));";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void insertPhoto(Connection connection, String table_name, String name, String address){
        Statement statement;
        try {
            String query = String.format("insert into %s(name, address) values('%s', '%s');", table_name, name, address);
            System.out.println("Inserted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
