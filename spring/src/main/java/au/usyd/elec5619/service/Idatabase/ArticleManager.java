package au.usyd.elec5619.service.Idatabase;

import java.util.List;
import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.Comment;

public interface ArticleManager {

    // CREATE

    public void addArticle(Article article);
    
    // READ

    public List<Article> getArticles();

    public Article getArticleById(long id);

    public List<Article> getArticlesByTag(String tag);

    public List<Article> getArticlesByCountry(String country);

    public List<Article> getArticlesByTitle(String title);

    public List<Article> getArticlesByAdmin();

    public List<Article> getArticlesByUsers();

    public List<Article> getArticlesBySource(String source);

    // UPDATE

    public void updateArticle(Article article);

    // DELETE

    public void deleteArticle(long id);

}