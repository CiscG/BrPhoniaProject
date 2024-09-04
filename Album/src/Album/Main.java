package Album;

import java.util.*;
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
        ShowPhoto showPhoto = new ShowPhoto();
        System.out.println("Insira o seu nome de usuario");
        //Input de usuario para verificação se existem arquivos desse usario, que caso negativo cria espaço no banco para esse usuario
        nameUser = scanner.nextLine();
        //Acessa ao banco de dados e verificação do usuario, o ideal seria ter feito apenas uma table para todos eles e feito uma divisão melhor para otimizar espaço
        Connection connection = db.connectionToDB("AlbumDB","postgres","123456");
        db.readData(connection, nameUser);
        status = db.status;
        test = "";
        if(status){
            System.out.println("Deseja escolher ou criar uma categoria?");
            test = scanner.nextLine();
        }
        if(Objects.equals(test, "criar") || Objects.equals(test, ""))
        {
            category = new Categories();
            status = true;
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
            System.out.println("""
                    Escolha a opção que deseja realizar\s
                    1 - Adicionar foto\s
                    2 - Remover foto\
                    
                    3 - Remover album\s
                    4 - Slides de fotos\s
                    5 - Ordenar Album\s
                    6 - Sair do programa""");
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
                    showPhoto.loadImageSlide(db.category);
                    break;
                case "5":
                    db.category.sort(Comparator.comparing(Categories::getId));
                    break;
                case "6":
                    status = false;
                    break;
            }
        }
    }

}