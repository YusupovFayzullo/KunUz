package WorkWithFiles;

import Database.Database;
import Entity.Article;
import Entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class WorkWithFiles {

        public static File userfile = new File("src/main/resources/users.json");
       public static File articlefile = new File("src/main/resources/article.json");

        static Gson gson=new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        static public void writeUser(){

            try (PrintWriter writer = new PrintWriter(userfile)) {
                writer.write(gson.toJson(Database.userList));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public static void readUser() {
            if(!userfile.exists()) return;

            try (FileReader reader = new FileReader(userfile)) {

                Type type = new TypeToken<List<User>>() {}.getType();

                List<User> users = gson.fromJson(reader, type);
                Database.userList.clear();
                Database.userList.addAll(users);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ///////////////

        static public void writeArticle(){

            try (PrintWriter writer = new PrintWriter(articlefile)) {
                writer.write(gson.toJson(Database.articles));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public static void readArticle() {
            if(!articlefile.exists()) return;

            try (FileReader reader = new FileReader(articlefile)) {

                Type type = new TypeToken<List<Article>>() {}.getType();

                List<Article> results = gson.fromJson(reader, type);
                Database.articles.clear();
                Database.articles.addAll(results);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



}
