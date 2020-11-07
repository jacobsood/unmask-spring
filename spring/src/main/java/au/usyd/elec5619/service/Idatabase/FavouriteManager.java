package au.usyd.elec5619.service.Idatabase;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;

import java.util.List;
import java.util.Set;


public interface FavouriteManager {
    // GET
    public Set<Article> getFavouriteArticlesByUsername(String username);
    public Set<Article> getFavouriteArticlesByUsername(String username, int maxNum);
    public int getLikesNumberByArticleID(long articleID);

    // ADD

    public boolean addFavourite(String username, Article article);

    // DELETE

    public String deleteLikedArticle(User user, Article article);

    public boolean checkLikeStatus(String username, Article article);

}