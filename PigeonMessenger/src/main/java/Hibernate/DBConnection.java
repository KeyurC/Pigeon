package Hibernate;

import Model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DBConnection {

    private static Session session;


    public static Session getSession() {
        return session;
    }

    public DBConnection() {
        session = new Configuration().
                configure().buildSessionFactory().openSession();

    }

}
