package Entity;

import Database.Database;
import Utils.ScannerUtil;
import WorkWithFiles.WorkWithFiles;

import java.util.List;

public class UserUI {

   private static List<Article>  articles = Database.articles;
   private static List<User>  userList = Database.userList;


    public static void page() {

        String name,phoneNumber;
        System.out.println();

        System.out.print("Fullname: ");
        name= ScannerUtil.str.nextLine();

        System.out.print("PhoneNumber: ");
        phoneNumber= ScannerUtil.str.nextLine();


        String check = check(name, phoneNumber);
        if(check.equals("succes")) {

            if(!checkBeforeLogin(name,phoneNumber)){
                User user=new User();

                user.setRole(List.of(Role.USER));
                user.setFullName(name);
                user.setPhoneNumber(phoneNumber);
                user.setPassword("user");

                userList.add(user);
                WorkWithFiles.writeUser();

            }

                if (articles.size()==0) {
                System.out.println("No article has been written yet");
                System.out.println();
                return;
            }
                if(!checkPublishArticle()){
                    System.out.println("Articles not yet published");
                    System.out.println();
                    return;
                }

            for (Article article : articles) {
                if (article.isPublishment()) {
                    System.out.println("Title "+article.getTitle());
                    System.out.println(article.getBody());
                    article.setReadNumber(article.getReadNumber()+1);
                    WorkWithFiles.writeArticle();
                    System.out.println("Number of view= "+article.getReadNumber());
                }
            }

            System.out.println();
        }
         else {
            System.out.println(check);
        }
    }

    private static boolean checkPublishArticle() {
        for (Article article : articles) {
            if(article.isPublishment()){
                return true;
            }
        }
        return  false;
    }

    private static boolean checkBeforeLogin(String name, String phoneNumber) {
        for (User user : userList) {
            if(user.getFullName().equals(name) && user.getPhoneNumber().equals(phoneNumber)){
                return true;
            }
        }
        return false;
    }

    private static String check(String name, String phoneNumber) {
        if(name.isBlank() && phoneNumber.isBlank()) return "Invaild name and phone number";
       if(name.isBlank())  return "Invalid name";
       if(phoneNumber.isBlank())  return "Invalid phoneNumber";

       return "succes";
    }
}
