package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;

import java.util.List;


public interface FavouriteManager {
    
    // READ

    public List<Article> getFavouriteArticlesByUserID(String userID);
    public List<Article> getFavouriteArticlesByUsername(String username);

    // ADD

    public void addFavourite(User user, Article article);

    // DELETE

    public void deleteLikedArticle(User user, Article article);
}