package au.usyd.elec5619.service.database;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.Idatabase.UserManager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "UserManager")
@Transactional
public class DatabaseUserManager implements UserManager {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUserById(Long id) {
        String hql = "FROM User WHERE id = :id";
        TypedQuery<User> userList = this.sessionFactory.getCurrentSession().createQuery(hql, User.class)
                .setParameter("id", id);
        User user = userList.getSingleResult();
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return this.sessionFactory.getCurrentSession().get(User.class, username);
    }

    @Override
    public String getUserSaltById(Long id) {
        User user = this.sessionFactory.getCurrentSession().get(User.class, id);
        return user.getSalt();
    }

    @Override
    public List<Article> getFavouriteArticlesByUserId(Long id) {
        User user = getUserById(id);
        return user.getFavouriteArticle();
        // String hql = 
        //             "SELECT a.*" +
        //             "FROM User AS u " +
        //             "JOIN UserFavourite AS uf " +
        //                 "ON u.id = uf.user_id " +
        //             "JOIN Article AS a " +
        //                 "ON a.id = uf.article_id " +
        //             "WHERE u.id = :id";
        // TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class);
        // List<Article> articleList = queryList.setParameter("id", id).getResultList();
        // return articleList;
    }

    @Override
    public List<Article> getViewedArticlesByUserId(Long id) {
        User user = getUserById(id);
        return user.getViewedArticles();
        // String hql = 
        //             "SELECT a.*" +
        //             "FROM User AS u " +
        //             "JOIN UserHistory AS uh " +
        //                 "ON u.id = uh.user_id " +
        //             "JOIN Article AS a " +
        //                 "ON a.id = uh.article_id " +
        //             "WHERE u.id = :id";
        // TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class);
        // List<Article> articleList = queryList.setParameter("id", id).getResultList();
        // return articleList;
    }

    @Override
    public void addArticleToFavourites(Long id, Article article) {
        User user = getUserById(id);
        List<Article> favouriteArticles = user.getFavouriteArticle();
        if (favouriteArticles == null) {
            favouriteArticles = new ArrayList<Article>();
        }

        if (favouriteArticles.contains(article)) return;

        favouriteArticles.add(article);
        user.setFavouriteArticle(favouriteArticles);
        this.sessionFactory.getCurrentSession().merge(user);
    }

    @Override
    public void addArticleToHistory(Long id, Article article) {
        User user = getUserById(id);
        List<Article> viewedArticles = user.getViewedArticles();
        if (viewedArticles == null) {
            viewedArticles = new ArrayList<Article>();
        }

        if (viewedArticles.contains(article));

        viewedArticles.add(article);
        user.setViewedArticles(viewedArticles);
        this.sessionFactory.getCurrentSession().merge(user);
    }

    @Override
    public void removeArticleFromFavourites(Long id, Article article) {
        User user = getUserById(id);
        List<Article> favouriteArticles = user.getFavouriteArticle();
        if (favouriteArticles == null) return;
        favouriteArticles.remove(article);
        user.setFavouriteArticle(favouriteArticles);
        this.sessionFactory.getCurrentSession().merge(user);
    }

}
