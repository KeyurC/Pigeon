package Hibernate;

import Model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DBConnection {

    private static Transaction transaction;
    private static Session session;

    public static Transaction getTransaction() {
        return transaction;
    }

    public static Session getSession() {
        return session;
    }

    public DBConnection() {
        session = new Configuration().
                configure().buildSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

}
