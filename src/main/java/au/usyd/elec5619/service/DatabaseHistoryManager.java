package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.TypedQuery;

@Service
@Transactional
public class DatabaseHistoryManager implements HistoryManager {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addHistoryByUser(User user, Article article) {
        List<Article> history = user.getReadHistory();
        history.add(article);
        user.setLikedArticle(history);
        this.sessionFactory.getCurrentSession().merge(user);
    }

    @Override
    public List<Article> getHistoryByUserID(String userID) {
        String sqlQuery =
                "FROM Article as a " +
                "JOIN a.wasRead AS aw " +
                "WHERE aw.id = :id";
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(sqlQuery, Article.class);
        List<Article> articleList = queryList.setParameter("id", userID).getResultList();
        return articleList;
    }

    @Override
    public void delHistoryByUser(User user, Article article) {
        List<Article> history = user.getReadHistory();
        history.remove(article);
        user.setLikedArticle(history);
        this.sessionFactory.getCurrentSession().merge(user);
    }
}
