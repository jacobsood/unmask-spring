package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// @Service(value = "articleManager")
@Transactional
public class DatabaseArticleManager implements ArticleManager {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // CREATE

    @Override
    public void addArticle(Article article) {
        this.sessionFactory.getCurrentSession().save(article);
    }

    // READ

    @Override
    public List<Article> getArticles() {
        List<Article> list = this.sessionFactory.getCurrentSession().createQuery("FROM Article").list();
        return list;
    }

    @Override
    public Article getArticleById(long id) {
        return (Article) this.sessionFactory.getCurrentSession().get(Article.class, id);
    }

    // Many to many relationship
    @Override
    public List<Article> getArticlesByTag(String tag) {
        String sqlQuery =
                    "FROM Article as a " +
                    "JOIN a.tags AS at " +
                    "WHERE at.id = :tag";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        List<Article> list = query.setParameter("tag", tag).list();
        return list;
    }

    // Many to one relationship
    @Override
    public List<Article> getArticlesByCountry(String country) {
        String sqlQuery = 
                        "FROM Article AS a " +
                        "JOIN a.country AS c " +
                        "WHERE c.id = :country";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        List<Article> list = query.setParameter("country", country).list();
        return list;
    }

    @Override
    public List<Article> getArticlesByTitle(String title) {
        String sqlQuery = 
                        "FROM Article as a " +
                        "WHERE a.title = :title";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        List<Article> list = query.setParameter("title", title).list();
        return list;
    }

    @Override
    public List<Article> getArticlesBySource(String source) {
        String sqlQuery = 
                        "FROM Article as a  " +
                        "WHERE a.source = :source";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        List<Article> list =  query.setParameter("source", source).list();
        return list;
    }

    // UPDATE

    @Override
    public void updateArticle(Article article) {
        this.sessionFactory.getCurrentSession().merge(article);
    }

    // DELETE

    @Override
    public void deleteArticle(long id) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        Article article = (Article) currentSession.get(Article.class, id);
        currentSession.delete(article);
    }
}