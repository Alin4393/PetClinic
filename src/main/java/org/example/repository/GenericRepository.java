package org.example.repository;

import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericRepository <X> {

    protected static SessionFactory sessionFactory = HibernateUtil.getSessionFactoryInstance();
    public void save(X x){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(x);
        transaction.commit();
        session.close();
    }


}
