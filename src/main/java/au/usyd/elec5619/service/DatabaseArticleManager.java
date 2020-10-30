package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Article;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatabaseArticleManager implements ArticleManager {

    @Autowired
    private SessionFactory sessionFactory;

    // CREATE

    @Transactional
    @Override
    public void addArticle(Article article) {
        this.sessionFactory.getCurrentSession().save(article);
    }

    // READ

    @Transactional
    @Override
    public List<Article> getArticles() {
        Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Article");
        final List<Article> list = new ArrayList<>();
        for (final Object o : query.list()) {
            list.add((Article) o);
        }
        return list;
    }

    @Transactional
    @Override
    public Article getArticleById(long id) {
        return (Article) this.sessionFactory.getCurrentSession().get(Article.class, id);
    }

    // Many to many relationship
    @Transactional
    @Override
    public List<Article> getArticlesByTag(String tag) {
        String sqlQuery =
                    "FROM Article as a " +
                    "JOIN a.tags AS at " +
                    "WHERE at.id = :tag";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        return query.setParameter("tag", tag).list();
    }

    // Many to one relationship
    @Transactional
    @Override
    public List<Article> getArticlesByCountry(String country) {
        String sqlQuery = 
                        "SELECT a.* FROM Article AS a " +
                        "JOIN a.country AS c " +
                        "WHERE c.id = :country";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        return query.setParameter("country", country).list();
    }

    @Transactional
    @Override
    public List<Article> getArticlesByTitle(String title) {
        String sqlQuery = 
                        "FROM Article as a " +
                        "WHERE a.title = :title";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        return (List<Article>) query.setParameter("title", title).list();
    }

    @Transactional
    @Override
    public List<Article> getArticlesBySource(String source) {
        String sqlQuery = 
                        "FROM Article as a + " + 
                        "WHERE a.source = :source";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        return query.setParameter("source", source).list();
    }

    // UPDATE

    @Transactional
    @Override
    public void updateArticle(Article article) {
        this.sessionFactory.getCurrentSession().merge(article);
    }

    // DELETE

    @Transactional
    @Override
    public void deleteArticle(long id) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        Article article = (Article) currentSession.get(Article.class, id);
        currentSession.delete(article);
    }
}