package com.example.JpaHibernateSession.dao;

import com.example.JpaHibernateSession.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserDAO {
    @Autowired
    public SessionFactory sessionFactory;

    @Transactional
    public void create(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Transactional
    public List<?> readAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }

    @Transactional
    public User readById(User user) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, user.getId());
    }

    @Transactional
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Transactional
    public void deleteById(User user) {
        Session session = sessionFactory.getCurrentSession();
        User tempUser = session.load(User.class, user.getId());
        if (tempUser != null) {
            session.delete(tempUser);
        }
    }

    @Transactional
    public void deleteAll() {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from User").executeUpdate();
        session.createNativeQuery("ALTER SEQUENCE persondb_person_id_seq RESTART;").executeUpdate();
        //for .createNativeQuery -> pom.xml-> hibernate-core not alpha!!
    }

}

