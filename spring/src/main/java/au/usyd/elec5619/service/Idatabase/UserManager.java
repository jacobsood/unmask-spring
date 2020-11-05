package au.usyd.elec5619.service.Idatabase;

import java.util.List;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;

public interface UserManager {

    // READ

    public User getUserById(Long id);

    public User getUserByUsername(String username);

    public String getUserSaltById(Long id);

    public List<Article> getFavouriteArticlesByUserId(Long id);

    public List<Article> getViewedArticlesByUserId(Long id);

    // CREATE

    public void addArticleToFavourites(Long id, Article article);

    public void addArticleToHistory(Long id, Article article);

    // DELETE

    public void removeArticleFromFavourites(Long id, Article article);
}
