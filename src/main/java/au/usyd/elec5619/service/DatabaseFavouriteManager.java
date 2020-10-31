package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.TypedQuery;

@Service
@Transactional
public class DatabaseFavouriteManager implements FavouriteManager {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Article> getFavouriteArticlesByUserID(String userID) {
        String hql = "FROM Article AS a " +
                    "JOIN a.likes AS al " +
                    "WHERE al.id = :id";
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class);
        List<Article> articleList = queryList.setParameter("id", userID).getResultList();
        return articleList;
    }

    @Override
    public List<Article> getFavouriteArticlesByUsername(String username) {
        String hql =
                "FROM Article as a " +
                "JOIN a.likes AS al " +
                "WHERE al.username = :username";
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class);
        List<Article> articleList = queryList.setParameter("username", username).getResultList();
        return articleList;
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
