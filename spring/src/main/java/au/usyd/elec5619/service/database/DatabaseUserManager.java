package au.usyd.elec5619.service.database;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.Comment;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.Idatabase.UserManager;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Service(value = "UserManager")
@Transactional
public class DatabaseUserManager implements UserManager {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserByUsername(String username) {
        String hql = "From User where username=:username";
        Query<User> query = this.sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @Override
    public String getUserSaltByUsername(String username) {
        User user = this.sessionFactory.getCurrentSession().get(User.class, username);
        return user.getSalt();

    }

    @Override
    public boolean checkEmailIsExist(String yourEmail) {
        String sqlQuery = "select count(email) from User where email = :yourEmail";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sqlQuery);
        Number count = (Number) query.setParameter("yourEmail", yourEmail).uniqueResult();
        int val = count.intValue();
        if (val==0){
            // email not exist
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void setNewPassword(User user) {
        this.sessionFactory.getCurrentSession().merge(user);
    }


}
