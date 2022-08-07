package com.example.DataJpaHibernateEntityManager.dao;

import com.example.DataJpaHibernateEntityManager.domain.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void create(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public List<User> readAll() {
        TypedQuery<User> typedQuery = entityManager.createQuery("select user from User as user", User.class);
        return typedQuery.getResultList();
    }

    @Transactional
    public User readById(User user) {
        return entityManager.find(User.class, user.getId());
    }

    @Transactional
    public void update(User user) {
       entityManager.merge(user);
    }

    @Transactional
    public void deleteById(User user) {
        User tempUser = entityManager.find(User.class, user.getId());
        if (tempUser != null) {
            entityManager.remove(tempUser);
        }
    }

    @Transactional
    public void deleteAll() {
        entityManager.createQuery("delete from User").executeUpdate();
        entityManager.createNativeQuery("ALTER SEQUENCE persondb_person_id_seq RESTART;").executeUpdate();
        //for .createNativeQuery -> pom.xml -> hibernate-core not alpha 'cause "not yet implemented".
    }

}

