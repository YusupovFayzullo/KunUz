package Entity;


import Database.Database;
import Service.UserService;
import SetExample.HashSetExample;
import Utils.ScannerUtil;
import WorkWithFiles.WorkWithFiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;

import static Database.Database.articles;

import static Service.ArticleService.checkId;

public class WriterUI {



    public static void page() {

        System.out.print("Enter password= ");
        String pas=ScannerUtil.str.nextLine();

        System.out.print("Enter id= ");
        int i=ScannerUtil.number.nextInt();

        User user1=new User();
        user1.setRole(List.of(Role.WRITER));

        User user = UserService.checkWriter(pas, i);
        if (user==null) {
            System.out.println("Incorrect password or id");
            System.out.println();
            return;
        }
        String s;
        while (true){
        System.out.println("1-> View self-authored articles ");
        System.out.println("2-> Write a new article ");
        System.out.println("0. Exit");


            s= ScannerUtil.str.nextLine();

            switch (s){
                case "0": return;
                case "1": allarticles(user); break;
                case "2": writeArticle(user); break;
                default:
                    Result.incorrect();
            }
        }
    }

    private static void writeArticle(User u) {
        String title;
        int id;
        String body;
        int writerId;


               System.out.print("Enter title: ");
               title = ScannerUtil.str.nextLine();

               System.out.print("Enter body: ");
               body = ScannerUtil.str.nextLine();



         System.out.print("Enter article id: ");
         id=ScannerUtil.number.nextInt();
        while(true) {
            for (Article article : articles) {
                if(article.getId()==id){
                    System.out.println("Please, enter other id ");
                    id = ScannerUtil.number.nextInt();
                }
            }
            break;
        }


        System.out.print("Enter writer id: ");
        writerId=ScannerUtil.number.nextInt();

        while(true) {
            for (Article article : articles) {
                if(article.getWriteId()==writerId){
                    System.out.println("Please, enter other id ");
                    writerId = ScannerUtil.number.nextInt();
                }
            }
            break;
        }
        String check = check(title, body, id, writerId);
        if(check.equals("succes")){

            Article article=new Article();

            article.setBody(body);
            article.setId(id);
            article.setTitle(title);
            article.setWriteId(writerId);
            article.setOwner(u.getFullName());

            LocalDateTime localDateTime=LocalDateTime.now();
            String format = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            article.setTime(format);

            articles.add(article);
            WorkWithFiles.writeArticle();
            WorkWithFiles.writeUser();
            System.out.println("successfully written");
            System.out.println();


        }else {
            System.out.println(check);
            System.out.println();
        }
    }

    private static String check(String title, String body, int id, int writerId) {
        if(title.isBlank() && body.isBlank())  return "Invalid title and body";
        if(title.isBlank()) return "Invalid title";
        if(body.isBlank()) return "Invalid body";
        if(String.valueOf(id).isBlank()) return "Article id can not zero";
        if(String.valueOf(writerId).isBlank()) return "WrittenId  can not zero";

        return "succes";

    }

    private static void allarticles(User user) {
     int c=0;

        for (Article article : articles) {
            if (article.getOwner().equals(user.getFullName())) {
                article.setReadNumber(article.getReadNumber() + 1);
                WorkWithFiles.writeArticle();
                WorkWithFiles.writeUser();
                System.out.println(article);
            } else{
                c++;
            }
        }
        if(c==articles.size()){
    System.out.println("You did not any article ");
    return;
}
        }


}
