package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Article;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

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
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery("FROM Article", Article.class);
        List<Article> articleList = queryList.getResultList();
        return articleList;
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
        String hql =
                    "FROM Article as a " +
                    "JOIN a.tags AS at " +
                    "WHERE at.id = :tag";
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class).setParameter("tag", tag);
        List<Article> articleList = queryList.getResultList();
        return articleList;
    }

    // Many to one relationship
    @Transactional
    @Override
    public List<Article> getArticlesByCountry(String country) {
        String hql = 
                        "SELECT a.* FROM Article AS a " +
                        "JOIN a.country AS c " +
                        "WHERE c.id = :country";
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class).setParameter("country", country);
        List<Article> articleList = queryList.getResultList();
        return articleList;
    }

    @Transactional
    @Override
    public List<Article> getArticlesByTitle(String title) {
        String hql = 
                        "FROM Article as a " +
                        "WHERE a.title = :title";
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class).setParameter("title", title);
        List<Article> articleList = queryList.getResultList();
        return articleList;
    }

    @Transactional
    @Override
    public List<Article> getArticlesBySource(String source) {
        String hql = 
                        "FROM Article as a + " + 
                        "WHERE a.source = :source";
        TypedQuery<Article> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Article.class).setParameter("source", source);
        List<Article> articleList = queryList.getResultList();
        return articleList;
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