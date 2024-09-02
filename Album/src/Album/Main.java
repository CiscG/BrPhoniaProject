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

        System.out.println("Deseja escolher ou criar uma categoria?");
        test = scanner.nextLine();
        if(Objects.equals(test, "criar"))
        {
            category = new Categories();
            while(status) {
                System.out.println("Digite o nome de sua categoria");
                category.category = scanner.nextLine();
                if(db.searchByCategory2(connection, nameUser, category.category)){
                    db.categoryTam = 0;
                    System.out.println("Categoria disponivel \nAgora escolha uma imagem para adicionar");
                    db.insertRow(connection,nameUser,category.category);
                    status = false;
                }
            }
        }else {
            System.out.println("Escolha a categoria que você deseja visitar");
            nameCategory = scanner.nextLine();
            status = db.searchByCategory(connection, nameUser, nameCategory);
            while (status) {
                System.out.println("Escolha  novamente a categoria que você deseja visitar");
                nameCategory = scanner.nextLine();
                status = db.searchByCategory(connection, nameUser, nameCategory);
            }
        }
        status = true;
        while(status){
            System.out.println("Escolha a opção que deseja realizar \n1 - Adicionar foto \n2 - Remover foto" +
                    "\n3 - Remover album \n4 - Slides de fotos \n5 - Sair do programa");
            test = scanner.nextLine();
            switch (test){
                case "1":
                    db.insertRow(connection, nameUser, db.categoryName);
                    break;
                case "2":
                    System.out.println("selecione a foto que você deseja deletar:");
                    test = scanner.nextLine();
                    db.deleteRowByImage(connection, nameUser, test);
                    break;
                case "3":
                    db.deleteRowByCategory(connection, nameUser, db.categoryName);
                    break;
                case "4":
                    //chamar slide de fotos
                    break;
                case "5":
                    status = false;
                    break;
            }
        }
    }

}