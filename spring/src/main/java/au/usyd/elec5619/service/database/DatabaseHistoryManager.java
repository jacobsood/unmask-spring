package au.usyd.elec5619.service.database;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.Idatabase.ArticleManager;
import au.usyd.elec5619.service.Idatabase.HistoryManager;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import au.usyd.elec5619.service.Idatabase.UserManager;

import javax.annotation.Resource;

@Service(value = "HistoryManager")
@Transactional
public class DatabaseHistoryManager implements HistoryManager {
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
    public void addHistoryByUser(User user, Article article) {
        Set<User> viewBy = article.getUsersViewedBy();
        viewBy.add(user);
        article.setUsersViewedBy(viewBy);
        this.sessionFactory.getCurrentSession().merge(article);
    }

    @Override
    public Set<Article> getHistoryByUsername(String username, int maxNum) {
        User user = userManager.getUserByUsername(username);
        return user.getHistory(maxNum);
    }

    @Override
    public Set<Article> getHistoryByUsername(String username) {
        User user = userManager.getUserByUsername(username);
        return user.getHistory();
    }

    @Override
    public void delHistoryByUser(User user, Article article) {
        Set<Article> history = user.getHistory();
        history.remove(article);
        user.setHistory(history);
        this.sessionFactory.getCurrentSession().merge(user);
    }
}
