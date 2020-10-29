package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "HistoryManager")
@Transactional
public class DatabaseHistoryManager implements HistoryManager {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        return query.setParameter("id", userID).list();
    }

    @Override
    public void delHistoryByUser(User user, Article article) {
        List<Article> history = user.getReadHistory();
        history.remove(article);
        user.setLikedArticle(history);
        this.sessionFactory.getCurrentSession().merge(user);
    }
}
