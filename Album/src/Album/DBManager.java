package Album;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;


public class DBManager {
    public BufferedImage photo;
    LoadImage loadImage = new LoadImage();
    public List<Categories> Category = new ArrayList<>();
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
            String query = "create Table " + table_name + "(ID SERIAL, category varchar(200), address varchar(200), primary key(ID));";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void insertRow(Connection connection, String table_name, String category, String address){
        Statement statement;
        try {
            //Fazer teste se existem 5 fotos dessa categoria
            String query = String.format("insert into %s(category, address) values('%s', '%s');", table_name, category, address);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println(loadImage.);
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
                System.out.print(rs.getString("ID")+" ");
                System.out.print(rs.getString("category")+" ");
                System.out.println(rs.getString("Address")+" ");
            }
        } catch (Exception e) {
            System.out.println(e);
            this.createTable(connection, "test");
        }
    }
    public void updateCategory(Connection connection, String table_name, String old_category, String new_category){
        Statement statement;
        try {
            String query = String.format("update %s set category = '%s' where category = '%s'", table_name, new_category, old_category);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void searchByCategory(Connection connection, String table_name, String category){
        Statement statement;
        ResultSet rs;
        try {
            String query  = String.format("select * from %s where category = '%s'", table_name, category);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("ID"));
                System.out.print(rs.getString("category"));
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
            String query  = String.format("select * from %s where ID = '%s'", table_name, id);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("ID"));
                System.out.print(rs.getString("category"));
                System.out.println(rs.getString("Address"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void deleteRowByCategory(Connection connection, String table_name, String category) {
        Statement statement;
        try{
            String query = String.format("delete from %s where category = '%s'", table_name, category);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void deleteRowByImage(Connection connection, String table_name, String image) {
        Statement statement;
        try{
            String query = String.format("delete from %s where address = '%s'", table_name, image);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void deleteTable(Connection connection, String table_name){
        Statement statement;
        try {
            String query = String.format("drop table %s", table_name);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void loadImage (String path){
        try {
            photo = ImageIO.read(DBManager.class.getResource(path));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
