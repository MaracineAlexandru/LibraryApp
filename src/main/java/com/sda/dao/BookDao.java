package com.sda.dao;

import com.sda.model.Book;
import com.sda.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDao extends GenericDao<Book> {
    public boolean isTitleUnique(String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String select="from Book b where b.title=:parameter";

        Query query=session.createQuery(select);

        query.setParameter("parameter",title);

        List list=query.list();
        session.close();

        return list.isEmpty();
    }
    public Book findBookByTitle(String title){
        Session session = HibernateUtil.getSessionFactory().openSession();

        String select="from Book b where b.title=:parameter";

        Query query=session.createQuery(select);

        query.setParameter("parameter",title);

        List<Book> list=query.list();
        session.close();

        return list.get(0);
    }
    public void updateEntity(Book entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }
}
