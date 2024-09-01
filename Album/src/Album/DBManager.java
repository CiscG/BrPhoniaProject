package Album;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//import java.io.IOException;
//import java.util.Objects;


public class DBManager {
    public BufferedImage photo;
    public int categoryTam;
    boolean status = true;
    LoadImage loadImage = new LoadImage();
    public List<Categories> category = new ArrayList<Categories>();
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
            String query = "create Table " + table_name + "(ID SERIAL, category varchar(200),filedata bytea(), primary key(ID));";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void insertRow(Connection connection, String table_name, String category){
        Statement statement;
        try {
            //Fazer teste se existem 5 fotos dessa categoria
            if (categoryTam < 5) {
                loadImage.loadImage(loadImage.OpenFileViaExplorer());
                String query = String.format("insert into %s(category, filedata) values('%s', '%s');", table_name, category);
                statement = connection.createStatement();
                statement.executeUpdate(query);
                //loadImage.OpenFileViaExplorer();
                photo = loadImage.loadImage(LoadImage.OpenFileViaExplorer());
                //System.out.println(loadImage.OpenFileViaExplorer());
                System.out.println("Inserted");
            }else{
                System.out.println("Categoria cheia");
            }

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
                System.out.println(rs.getString("data")+" ");
            }
        } catch (Exception e) {
            System.out.println(e);
            this.createTable(connection, table_name);
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
    public void searchByCategory(Connection connection, String table_name, String nameCategory){
        Categories cat = new Categories();
        Statement statement;
        ResultSet rs;
        this.category = new ArrayList<Categories>();
        try {
            String query  = String.format("select * from %s where category = '%s'", table_name, nameCategory);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                cat.id = rs.getInt("ID");
                cat.category = rs.getString("category");
                cat.photo = ImageIO.read(new ByteArrayInputStream(loadImage.imageBytes));
                category.add(cat);
            }
            categoryTam = category.size();
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
