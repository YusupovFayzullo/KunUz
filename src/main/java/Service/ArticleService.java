package Service;

import Database.Database;
import Entity.Article;
import WorkWithFiles.WorkWithFiles;

import java.util.List;

public class ArticleService {
    private static List<Article> articles = Database.articles;

    public static int checkId(int id) {

        for (Article article : articles) {
            if(article.getId()==id && !article.isPublishment()){
                article.setPublishment(true);
                WorkWithFiles.writeArticle();
                return 0;
            }
        }

        return -1;
    }
}
