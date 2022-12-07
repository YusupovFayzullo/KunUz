package Service;

import Database.Database;
import Entity.Article;
import Entity.Role;
import Entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    public static List<User> userList=Database.userList;
    public static List<Article> writerArticles=new ArrayList<>();


    public static User checkModeriter(String pasword, int id) {
        for (User user : userList) {
          if(user.getPassword().equals(pasword) && user.getId()==id && user.getRole().contains(Role.MODERATOR)) {
              return user;
          }
        }
        return null;
    }

    public static User checkWriter(String pas, int i) {

        for (User user : userList) {
            if(user.getPassword().equals(pas) && user.getId()==i && user.getRole().contains(Role.WRITER)) {
                return user;
            }
        }
        return null;
    }

    public static User checkAdmin(String pass, int id) {
        for (User user : userList) {
            if(user.getPassword().equals(pass) && (user.getId() == id) && user.getRole().contains(Role.ADMIN)) {
                return user;
            }
        }
        return null;
    }


}
