package au.usyd.elec5619.service.database;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.service.Idatabase.ArticleManager;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "ArticleManager")
@Transactional
public class DatabaseArticleManager implements ArticleManager {

    @Autowired
    private SessionFactory sessionFactory;

    // CREATE

    @Override
    public void addArticle(Article article) {
        this.sessionFactory.getCurrentSession().save(article);
    }

    // READ

    @Override
    public List<Article> getArticles() {
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery("FROM Article", Article.class);
        List<Article> articleList = queryList.getResultList();
        return articleList;
    }

    @Override
    public Article getArticleById(long id) {
        return this.sessionFactory.getCurrentSession().get(Article.class, id);
    }

    // Many to many relationship
    @Override
    public List<Article> getArticlesByTag(String tag) {
        String hql =
                    "FROM Article as a " +
                    "JOIN a.tags AS at " +
                    "WHERE at.id = :tag";
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class).setParameter("tag", tag);
        List<Article> articleList = queryList.getResultList();
        return articleList;
    }

    @Override
    public List<Article> getArticlesByCountry(String country) {
        String hql = 
                    "FROM Article  " +
                    "WHERE country = :country";
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class).setParameter("country", country);
        List<Article> articleList = queryList.getResultList();
        return articleList;
    }

    @Override
    public List<Article> getArticlesByTitle(String title) {
        String hql = 
                    "FROM Article " +
                    "WHERE title = :title";
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class).setParameter("title", title);
        List<Article> articleList = queryList.getResultList();
        return articleList;
    }

    @Override
    public List<Article> getArticlesBySource(String source) {
        String hql = 
                    "FROM Article " + 
                    "WHERE source = :source";
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class).setParameter("source", source);
        List<Article> articleList = queryList.getResultList();
        return articleList;
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

    @Override
    public List<Article> getArticlesByAdmin() {
        String hql = 
                    "FROM Article " +
                    "WHERE created_by_admin";
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class);
        List<Article> articleList = queryList.getResultList();
        return articleList;
    }

    @Override
    public List<Article> getStories() {
        String hql = 
                    "FROM Article " +
                    "WHERE NOT created_by_admin";
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class);
        List<Article> articleList = queryList.getResultList();
        return articleList;
    }
  
}