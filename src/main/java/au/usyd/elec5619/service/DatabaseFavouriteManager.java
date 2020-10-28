package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.ArticleFavourite;
import au.usyd.elec5619.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "FavouriteManager")
@Transactional
public class DatabaseFavouriteManager implements FavouriteManager {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Article> getFavouriteArticlesByUserID(String userID) {
        String sqlQuery =
                "FROM Article as a " +
                "JOIN a.likes AS al " +
                "WHERE al.id = :id";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        return query.setParameter("id", userID).list();
    }

    @Override
    public List<Article> getFavouriteArticlesByUsername(String username) {
        String sqlQuery =
                "FROM Article as a " +
                "JOIN a.likes AS al " +
                "WHERE al.username = :username";

        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        return query.setParameter("username", username).list();
    }

    @Override
    public void addFavourite(User user, Article article) {
        List<Article> like = user.getLikedArticle();
        like.add(article);
        user.setLikedArticle(like);
        this.sessionFactory.getCurrentSession().merge(user);
    }

    @Override
    public void deleteLikedArticle(User user, Article article) {
        List<Article> like = user.getLikedArticle();
        like.remove(article);
        user.setLikedArticle(like);
        this.sessionFactory.getCurrentSession().merge(user);
    }


}
