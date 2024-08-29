package Album;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBManager {
    public Connection connectionToDB(String dbname, String user, String pwrd) {
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
    public void insertRow(Connection connection, String table_name, String name, String address){
        Statement statement;
        try {
            String query = String.format("insert into %s(name, address) values('%s', '%s');", table_name, name, address);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Inserted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void readData(Connection connection, String table_name){
        Statement statement;
        ResultSet rs;
        try{
            String query = String.format("select  * from %s", table_name);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("employID")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("Address")+" ");
            }
        } catch (Exception e) {
            System.out.println(e);
            //createTable(connection, "test");
        }
    }
    public void updateName(Connection connection, String table_name, String old_name, String new_name){
        Statement statement;
        try {
            String query = String.format("update %s set name = '%s' where name = '%s'", table_name, new_name, old_name);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void searchByCategory(Connection connection, String table_name, String name){
        Statement statement;
        ResultSet rs;
        try {
            String query  = String.format("select * from %s where name = '%s'", table_name, name);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("employID"));
                System.out.print(rs.getString("name"));
                System.out.println(rs.getString("Address"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void searchByID(Connection connection, String table_name, int id){
        Statement statement;
        ResultSet rs;
        try {
            String query  = String.format("select * from %s where employID = '%s'", table_name, id);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("employID"));
                System.out.print(rs.getString("name"));
                System.out.println(rs.getString("Address"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void deleteRowByName(Connection connection, String table_name, String name)
    {
        Statement statement;
        try{
            String query = String.format("delete from %s where name = '%s'", table_name, name);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void deleteRowByID(Connection connection, String table_name, int id)
    {
        Statement statement;
        try{
            String query = String.format("delete from %s where employID = '%s'", table_name, id);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void deleteTable(){

    }
}
