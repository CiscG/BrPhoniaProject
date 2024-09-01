package Album;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Categories category;
        String nameUser, nameCategory, test;
        DBManager db = new DBManager();
        BufferedImage photo;
        boolean status = true;
        System.out.println("Insira o seu nome de usuario");
        //Input de usuario para verificação se existem arquivos desse usario, que caso negativo cria espaço no banco para esse usuario
        nameUser = scanner.nextLine();
        //Acessa ao banco de dados e verificação do usuario, o ideal seria ter feito apenas uma table para todos eles e feito uma divisão melhor para otimizar espaço
        Connection connection = db.connectionToDB("AlbumDB","postgres","123456");
        db.readData(connection,nameUser);
        while(status) {
            System.out.println("Deseja escolher ou criar uma categoria?");
            test = scanner.nextLine();
            if(Objects.equals(test, "criar"))
            {
                category = new Categories();
                System.out.println("Digite o nome de sua categoria");
                category.category = scanner.nextLine();
                if(db.searchByCategory2(connection, nameUser, category.category)){
                    db.categoryTam = 0;
                    System.out.println("Categoria disponivel \nAgora escolha uma imagem para adicionar");
                    db.insertRow(connection,nameUser,category.category);
                }

            }else {
                System.out.println("Escolha a categoria que você deseja visitar");
                nameCategory = scanner.nextLine();
                db.searchByCategory(connection, nameUser, nameCategory);
                status = db.status;
            }

        }
    }

}