package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "RegistrationManager")
@Transactional
public class DatabaseRegistrationManager implements RegistrationManager{

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void registration(User user) {
        Session session=sessionFactory.getCurrentSession();
        session.save(user);
    }
}
