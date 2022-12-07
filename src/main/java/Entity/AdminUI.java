package Entity;

import Database.Database;
import Service.UserService;
import SetExample.HashSetExample;
import Utils.ScannerUtil;
import WorkWithFiles.WorkWithFiles;

import java.util.HashSet;
import java.util.List;

import static Service.ArticleService.checkId;

public class AdminUI {
   private static List<User> userList = Database.userList;
   private static List<Article> articles = Database.articles;
   public static HashSet<Integer> userId=HashSetExample.userId;
    public static void page() {
        String password;
        int id;
        System.out.print("Password: ");
        password= ScannerUtil.str.nextLine();


        System.out.print("Id= ");
        id=ScannerUtil.number.nextInt();

        String check = check(password, id);
        if(check.equals("succes")) {
            userId.add(id);

            User user = UserService.checkAdmin(password, id);

            if (user != null) {
                adminWorks(user);
            } else {
                System.out.println("Incorrect password or id ");
                System.out.println();
                return;
            }
        }else {
            System.out.println(check);
            System.out.println();

        }

    }

    private static void adminWorks(User user) {
        String select;
        while (true) {

            System.out.println("1. Show all users");
            System.out.println("2. Assign roles to other users");
            System.out.println("3. View all articles");
            System.out.println("4.Exit");

            select = ScannerUtil.str.nextLine();

            switch (select) {
                case "1":
                    showUsers();
                    break;
                case "2":
                    assignRole();
                    break;
                case "3":
                    showArticles();
                    break;
                case "4": return;
                default:
                    Result.incorrect();
            }
        }
    }

    private static void showArticles() {
        if (articles.size()==0) {
            System.out.println("No article has been written yet");
            System.out.println();
            return;
        }

        for (Article article : articles) {
            article.setReadNumber(article.getReadNumber()+1);
            WorkWithFiles.writeArticle();
            System.out.println(article);

        }
        System.out.println();
    }

    private static void assignRole() {
        int u,r;
        System.out.println("Which user do you want to assign a role to ?");

        for (int i = 0; i < userList.size(); i++) {
            System.out.println((i+1)+"-> "+userList.get(i));
        }
        System.out.println();
        u=ScannerUtil.number.nextInt();
        if(u<=0 || u>userList.size()){
            Result.incorrect();
            return;
        }
        System.out.println("What role do you want to give");
        System.out.println("1->"+Role.ADMIN);
        System.out.println("2->"+Role.WRITER);
        System.out.println("3->"+Role.MODERATOR);

        System.out.println();
        r=ScannerUtil.number.nextInt();

        System.out.print("Give the password: ");
        String p=ScannerUtil.str.nextLine();

        System.out.print("Give the id: ");
        int ix=ScannerUtil.number.nextInt();

        int i = checkuserId(ix);
        while(true) {
            for (User user : userList) {
                if (user.getId() == ix) {
                    System.out.println("Please, enter other id");
                    ix = ScannerUtil.number.nextInt();
                }
            }
            break;
        }


        String check = check(p, ix);
            if (check.equals("succes")) {
               userId.add(ix);

                userList.get(u - 1).setPassword(p);
                userList.get(u - 1).setId(ix);


                switch (r) {

                    case 1:
                        userList.get(u - 1).getRole().add(Role.ADMIN);
                        break;
                    case 2:
                        userList.get(u - 1).getRole().add(Role.WRITER);
                        break;
                    case 3:
                        userList.get(u - 1).getRole().add(Role.MODERATOR);
                        break;

                    default:
                        Result.incorrect();
                }
                WorkWithFiles.writeUser();
                System.out.println("Success");
                System.out.println();
                System.out.println();
            } else {
                System.out.println(check);
                System.out.println();

            }
        }



    public static int  checkuserId(int ix) {
        for (User user : userList) {
            if(user.getId()==ix){
                return -1;
            }
        }
        return 0;
    }

    private static String check(String p, int ix) {
        if(p.isBlank() && String.valueOf(ix).isBlank()) return "Invalid pasword and id";
        if(p.isBlank()) return "Invalid pasword";
        if(ix==0)  return "Invalid id";
        return "succes";
    }

    private static void showUsers() {
        for (User user : userList) {
            System.out.println(user);
        }
        System.out.println();

    }

}
