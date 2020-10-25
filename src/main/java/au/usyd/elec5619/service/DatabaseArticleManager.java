package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Article;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value = "articleManager")
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
        return this.sessionFactory.getCurrentSession().createQuery("FROM Article").list();
    }

    @Override
    public Article getArticleById(long id) {
        return (Article) this.sessionFactory.getCurrentSession().get(Article.class, id);
    }

    // Many to many relationship
    @Override
    public List<Article> getArticlesByTag(String tag) {
        String sqlQuery =
                    "SELECT a.* FROM Article AS a " +
                    "INNER JOIN ArticleTag AS at " +
                        "ON a.id = at.article_id " +
                    "INNER JOIN Tag AS t " +
                        "ON at.tag_id = t.id " +
                    "WHERE t.id = :tag";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        return query.setParameter("tag", tag).list();
    }

    // Many to one relationship
    @Override
    public List<Article> getArticlesByCountry(String country) {
        String sqlQuery = 
                        "SELECT a.* FROM Article AS a " +
                        "JOIN a.country AS c " +
                        "WHERE c.country = :country";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        return query.setParameter("country", country).list();
    }

    @Override
    public List<Article> getArticlesByTitle(String title) {
        String sqlQuery = 
                        "FROM Article as a " +
                        "WHERE a.title = :title";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        return query.setParameter("title", title).list();
    }

    @Override
    public List<Article> getArticlesBySource(String source) {
        String sqlQuery = 
                        "FROM Article as a + " + 
                        "WHERE a.source = :source";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        return query.setParameter("source", source).list();
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