package com.sda.dao;

import com.sda.model.Author;
import com.sda.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AuthorDao extends GenericDao<Author> {
    public boolean isAuthorUnique(String lastName) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String select="from Author a where a.lastName=:parameter";

        Query query=session.createQuery(select);

        query.setParameter("parameter",lastName);

        List list=query.list();
        session.close();

        return list.isEmpty();
    }

    public Author findAuthorByLastName(String lastName){
        Session session = HibernateUtil.getSessionFactory().openSession();

        String select="from Author a where a.lastName=:parameter";

        Query query=session.createQuery(select);

        query.setParameter("parameter",lastName);

        List<Author> list=query.list();
        session.close();

        return list.get(0);
    }
    public void updateEntity(Author entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }
}
