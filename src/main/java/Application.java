import Database.Database;
import Entity.Article;
import Entity.MainUI;
import Entity.Role;
import Entity.User;
import WorkWithFiles.WorkWithFiles;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        WorkWithFiles.readUser();
        WorkWithFiles.readArticle();

        MainUI.run();


     }
}
