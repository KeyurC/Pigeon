package Hibernate;

import Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HibernateUtil {
    private static HibernateUtil instance = new HibernateUtil();
    private SessionFactory sessionFactory;

    public static HibernateUtil getInstance(){
        return instance;
    }

    private HibernateUtil() {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();

    }

    public static Session getSession() {
        Session session = getInstance().sessionFactory.openSession();
        return session;
    }

}
