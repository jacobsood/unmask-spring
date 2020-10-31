package au.usyd.elec5619.service;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.usyd.elec5619.domain.Country;

@Service
@Transactional
public class DatabaseCountryManager implements CountryManager {
   
    @Autowired
    private SessionFactory sessionFactory;

    // CREATE

    @Override
    public void addCountry(Country country) {
        this.sessionFactory.getCurrentSession().save(country);
    }

    // READ

    @Override
    public Country getCountry(long id) {
        return (Country) this.sessionFactory.getCurrentSession().get(Country.class, id);
    }

    @Override
    public Country getCountryByName(String country) {
        String hql = 
                    "FROM Country + " +
                    "WHERE country = :country";
        TypedQuery<Country> queryList = this.sessionFactory.getCurrentSession().createQuery(hql, Country.class).setParameter("country", country);
        return queryList.getSingleResult();
    }

    @Override
    public void updateCountry(Country country) {
        this.sessionFactory.getCurrentSession().merge(country);

    }

    @Override
    public void deleteCountry(long id) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        Country country = (Country) currentSession.get(Country.class, id);
        currentSession.delete(country);
    }
}
