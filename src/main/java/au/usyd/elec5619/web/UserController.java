package au.usyd.elec5619.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.service.Idatabase.UserManager;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserManager userManager;

    // READ

    @GetMapping(value = "/{id}/favourites")
    public List<Article> getFavouriteArticlesByUserId(@PathVariable("id") Long id) {
        return userManager.getFavouriteArticlesByUserId(id);
    }

    @GetMapping(value = "/{id}/viewed_articles")
    public List<Article> getViewedArticlesByUserId(@PathVariable("id") Long id) {
        return userManager.getViewedArticlesByUserId(id);
    }

    // ADD

    @PostMapping(value = "/{id}/favourites")
    public void addArticleToFavourites(@RequestBody Article article, @PathVariable("id") Long id) {
        userManager.addArticleToFavourites(id, article);
    }

    @PostMapping(value = "/{id}/viewed_articles")
    public void addArticleToHistory(@RequestBody Article article, @PathVariable("id") Long id) {
        userManager.addArticleToHistory(id, article);
    }

    // DELETE
    
    @DeleteMapping(value = "/{id}/favourites")
    public void removeArticleFromFavourites(@RequestBody Article article, @PathVariable("id") Long id) {
        userManager.removeArticleFromFavourites(id, article);
    }

}
