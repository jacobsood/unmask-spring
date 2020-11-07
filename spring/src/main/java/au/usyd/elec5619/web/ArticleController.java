package au.usyd.elec5619.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import au.usyd.elec5619.service.Idatabase.ArticleManager;
import au.usyd.elec5619.domain.Article;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleManager articleManager;
    
    @GetMapping
    public List<Article> getAll() {
        return articleManager.getArticles();
    }

    @GetMapping(value = "/id/{id}")
    public Article findById(@PathVariable("id") Long id) {
        return articleManager.getArticleById(id);
    }

    @GetMapping(value = "/tag/{tag}")
    public List<Article> getArticlesByTag(@PathVariable("tag") String tag) {
        return articleManager.getArticlesByTag(tag);
    }

    @GetMapping(value = "/country/{country}")
    public List<Article> getArticlesByCountry(@PathVariable("country") String country) {
        return articleManager.getArticlesByCountry(country);
    }

    @GetMapping(value = "/title/{title}")
    public List<Article> getArticlesByTitle(@PathVariable("title") String title) {
        return articleManager.getArticlesByTitle(title);
    }

    @GetMapping(value = "/by/admin")
    public List<Article> getArticlesCreatedByAdmin() {
        return articleManager.getArticlesByAdmin();
    }

    @GetMapping(value = "/by/users")
    public List<Article> getArticlesCreatedByUsers() {
        return articleManager.getArticlesByUsers();
    }

    @GetMapping(value = "/by/{source}")
    public List<Article> getArticlesBySource(@PathVariable("source") String source) {
        return articleManager.getArticlesBySource(source);
    }

    @PostMapping
    public void addArticle(@RequestBody Article article) {
        articleManager.addArticle(article);
    }

    @PutMapping
    public void updateArticle(@RequestBody Article article) {
        articleManager.updateArticle(article);
    }

    @DeleteMapping(value = "/id/{id}")
    public void deleteArticle(@PathVariable("id") Long id) {
        articleManager.deleteArticle(id);
    }
}