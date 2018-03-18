package com.example.janus.repository;


import com.example.janus.model.Ciupici;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.Optional;

@Repository
@Transactional
public class CiupiciRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public Serializable save(final Ciupici ciupici) {
        return getSession().save(ciupici);
    }

    public Optional<Ciupici> findById(final Long id) {
        final Session session = getSession();
        return session.byId(Ciupici.class).loadOptional(id);
    }

    private Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).getCurrentSession();
    }


}
