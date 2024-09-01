package Album;

import javax.swing.*;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nameUser, nameCategory;
        DBManager db = new DBManager();
        BufferedImage photo;
        System.out.println("Insira o seu nome de usuario");
        //Input de usuario para verificação se existem arquivos desse usario, que caso negativo cria espaço no banco para esse usuario
        nameUser = scanner.nextLine();
        //Acessa ao banco de dados e verificação do usuario, o ideal seria ter feito apenas uma table para todos eles e feito uma divisão melhor para otimizar espaço
        Connection connection = db.connectionToDB("AlbumDB","postgres","123456");
        db.readData(connection,nameUser);
        System.out.println("Escolha a categoria que você deseja visitar");

        nameCategory = scanner.nextLine();
        db.searchByCategory(connection, nameUser, nameCategory);
        //photo = loadImage.loadImage("asdasdasd");

        //db.insertRow(connection, nameUser, "gasd","Brazil");
        /*
        db.updateCategory(connection, nameUser, "Gab", "Gabriel");

        db.deleteRowByCategory(connection, nameUser, "gasd");
        db.deleteRowByImage(connection,nameUser, 1);
        db.deleteTable(connection, nameUser);
        */

    }

}