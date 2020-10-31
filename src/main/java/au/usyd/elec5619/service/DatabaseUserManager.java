package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        User user = this.sessionFactory.getCurrentSession().get(User.class, username);
        return user;
    }

    @Override
    public String getUserSaltByUsername(String username) {
        User user = this.sessionFactory.getCurrentSession().get(User.class, username);
        return user.getSalt();

    }

}
