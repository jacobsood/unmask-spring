package au.usyd.elec5619.service.database;

import au.usyd.elec5619.domain.Login;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.Idatabase.LoginManager;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DatabaseLoginManager implements LoginManager{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public boolean check(Login login) {
        // check if username in database
        String sql = "FROM User " + 
                    "WHERE username = :uname ";
        TypedQuery<User> queryList = this.sessionFactory.getCurrentSession().createQuery(sql, User.class);
        List<User> userList = queryList.setParameter("uname", login.getId()).getResultList();
        int count =  userList.size();
        return count>0?true:false;
    }

    @Override
    public Boolean loginStatusByUsername(String username) {
        User user = this.sessionFactory.getCurrentSession().get(User.class, username);
        return user.getLoginStatus();
    }

    @Override
    public void setLoginStatus(String username) {
        User user = this.sessionFactory.getCurrentSession().get(User.class, username);
        user.setLoginStatus(!user.getLoginStatus());
    }
}



