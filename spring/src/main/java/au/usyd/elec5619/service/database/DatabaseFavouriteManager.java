package au.usyd.elec5619.service.database;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.Idatabase.ArticleManager;
import au.usyd.elec5619.service.Idatabase.FavouriteManager;
import au.usyd.elec5619.service.Idatabase.UserManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service(value = "FavouriteManager")
@Transactional
public class DatabaseFavouriteManager implements FavouriteManager {
    private SessionFactory sessionFactory;
    @Resource(name = "UserManager")
    private UserManager userManager;
    @Resource(name = "ArticleManager")
    private ArticleManager articleManager;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Set<Article> getFavouriteArticlesByUsername(String username, int maxNum) {
        User user = userManager.getUserByUsername(username);
        return user.getFavouriteArticles(5);
    }

    @Override
    public Set<Article> getFavouriteArticlesByUsername(String username) {
        User user = userManager.getUserByUsername(username);
        return user.getFavouriteArticles();
    }

    @Override
    public boolean addFavourite(String username, Article article) {
        boolean status;
        if (article==null || username==null){
            return false;
        }
        User user = userManager.getUserByUsername(username);

        Set<User> favouritedBy = article.getFavouritedBy();
        if (!favouritedBy.contains(user)){
            favouritedBy.add(user);
            status =  true;
        }else{
            favouritedBy.remove(user);
            status = false;
        }
        article.setFavouritedBy(favouritedBy);

        this.sessionFactory.getCurrentSession().merge(article);
        return status;
    }

    @Override
    public int getLikesNumberByArticleID(long article_id) {
        Article article = articleManager.getArticleById(article_id);
        return article.getFavouritedBy().size();
    }



    @Override
    public String deleteLikedArticle(User user, Article article) {
        Set<Article> favouriteArticles = user.getFavouriteArticles();
        if (!favouriteArticles.contains(article)){
            return "article not exist.";
        }else{
            favouriteArticles.remove(article);
            return "dislike success";
        }
    }

    @Override
    public boolean checkLikeStatus(String username, Article article) {
        Set<Article> likeArticles = getFavouriteArticlesByUsername(username);
        if (likeArticles.contains(article)){
            return true;
        }else{
            return false;
        }
    }


}
