package au.usyd.elec5619.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.service.Idatabase.UserManager;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserManager userManager;

    @GetMapping(value = "/{id}/favourites")
    public List<Article> getFavouriteArticlesByUserID(@PathVariable("id") Long id) {
        return userManager.getFavouriteArticlesByUserId(id);
    }

    @PostMapping(value = "/{id}/favourites")
    public void addArticleToFavourites(@RequestBody Article article) {
        //User user = userManager.g
    }
}
