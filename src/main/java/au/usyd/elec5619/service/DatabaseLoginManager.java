package au.usyd.elec5619.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import au.usyd.elec5619.domain.Login;
import au.usyd.elec5619.domain.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

public class DatabaseLoginManager implements LoginManager{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public boolean check(Login login) {
        // check if username in database
        String sql = "select count(*) from User where username = :uname ";
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setParameter("uname", login.getId());
        int count =  (int)query.uniqueResult();
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
        Boolean status = user.getLoginStatus();
        user.setLoginStatus(status==Boolean.FALSE?Boolean.TRUE:Boolean.FALSE);
    }
}



