package com.example.janus.repository;


import com.example.janus.model.Ciupici;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

@Repository
@Transactional
@Slf4j
public class CiupiciRepository implements InitializingBean {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private SessionFactory sessionFactory;


    public Serializable save(final Ciupici ciupici) {
        return getSession().save(ciupici);
    }

    public Optional<Ciupici> findById(final Long id) {
        final Session session = getSession();
        return session.byId(Ciupici.class).loadOptional(id);
    }

    public void findAll() {
        Stream<Ciupici> ciupiciStream = returnFromSession(session ->
                session.createQuery("from Ciupici ciupici", Ciupici.class)
//                        .addQueryHint("org.hibernate.readOnly=true")
                        .stream());

        ciupiciStream
//                .map(b -> b.getName() + " was created on " + b.getCreateDateTime())
                .forEach(m -> {
                });

        ciupiciStream = returnFromSession(session ->
                session.createQuery("from Ciupici ciupici", Ciupici.class)
                        .addQueryHint("org.hibernate.readOnly=true")
                        .stream());

        ciupiciStream
//                .map(b -> b.getName() + " was created on " + b.getCreateDateTime())
                .forEach(m -> {
                });

    }


    protected void doInTransaction(HibernateTransactionConsumer callable) {
        Session session = null;
        session = getSession();
        callable.accept(session);
    }

    protected <T> T returnFromSession(HibernateTransactionFunction<T> callable) {
        T result = null;
        Session session = getSession();
        result = callable.apply(session);
        return result;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }


    @FunctionalInterface
    protected interface HibernateTransactionFunction<T> extends Function<Session, T> {
    }

    @FunctionalInterface
    protected interface HibernateTransactionConsumer extends Consumer<Session> {
    }
}
