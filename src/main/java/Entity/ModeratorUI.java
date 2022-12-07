package Entity;

import Database.Database;
import Service.ArticleService;
import Service.UserService;
import SetExample.HashSetExample;
import Utils.ScannerUtil;
import WorkWithFiles.WorkWithFiles;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

import java.util.List;



public class ModeratorUI {
    private static List<Article> articles = Database.articles;

    public static void page() {
        String pasword;
        int id;
        System.out.print("Enter password= ");
        pasword=ScannerUtil.str.nextLine();

        System.out.print("Enter id=");
        id=ScannerUtil.number.nextInt();


        User check = UserService.checkModeriter(pasword, id);
        if (check==null) {
            System.out.println("Incorrect pasword or id ");
            System.out.println();
            return;
        }
        String s;
        System.out.println("1. Publishing articles");
        System.out.println("0. Exit");
        s= ScannerUtil.str.nextLine();
        if(s.equals("1")){
            publishArticle();
            return;

        }


    }

    private static void publishArticle() {
        int c=0;
        if (articles.size()==0) {
            System.out.println("Articles are not yet");
            System.out.println();
            return;
        }
        System.out.println("These articles have not been published");
        for (Article article : articles) {
            if(!article.isPublishment()){
                System.out.println(article);
            } else {
                c++;
            }
        }

        System.out.println();
        if(c==articles.size()){
            System.out.println("All articles are published");
            return;
        }
        System.out.println("Which article you want to publish ? ");
        System.out.print("Please, enter its id= ");

        int Id=ScannerUtil.number.nextInt();

        int i = ArticleService.checkId(Id);
        if(i==0){
            System.out.println("successfully published");
            HashSetExample.moderateId.add(i);
            System.out.println();

        }
        else {
            System.out.println("Not id in articles");
        }

    }
}
