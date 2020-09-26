package Hibernate;

import Model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HibernateUtil {
    private HibernateUtil instance = new HibernateUtil();
    private static Session session;

    private HibernateUtil() {
        session = new Configuration().
                configure().buildSessionFactory().openSession();

    }

    public static Session getSession() {
        return session;
    }

}
