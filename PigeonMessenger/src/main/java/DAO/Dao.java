package DAO;

import Hibernate.DBConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface Dao<E> {
    Session session = DBConnection.getSession();
    Transaction transaction = DBConnection.getTransaction();

    void save(E e);
    void update(E e);
    void delete(E e);
}
