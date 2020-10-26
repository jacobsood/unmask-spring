package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DatabaseCommentManager implements CommentManager{

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addComment(Comment comment) {
        this.sessionFactory.getCurrentSession().save(comment);
    }

    @Override
    public Comment getCommentById(long commentID) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        return (Comment) currentSession.get(Comment.class, commentID);
    }

    @Override
    public List<Comment> getCommentsByArticle(long articleID) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        String hql = "FROM Comment AS C WHERE C.article_id = :articleID";
        Query query = currentSession.createQuery(hql);
        query.setParameter("articleID", articleID);
        return query.list();
    }

    @Override
    public List<Comment> getCommentsByAuthor(long authorID) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        String hql = "FROM Comment AS C WHERE C.author_id = :authorID";
        Query query = currentSession.createQuery(hql);
        query.setParameter("authorID", authorID);
        return query.list();
    }

    @Override
    public void updateComment(Comment comment) {
        this.sessionFactory.getCurrentSession().merge(comment);
    }

    @Override
    public void deleteComment(long commentID) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        Comment comment = (Comment) currentSession.get(Comment.class, commentID);
        currentSession.delete(comment);
    }
}
