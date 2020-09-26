package DAO.IDAO;

import Hibernate.HibernateUtil;
import org.hibernate.Session;

/**
 * General Interface for all Data Access
 * Objects with common methods
 * @param <E> Generic Object as implementing classes
 *           will use different models.
 */
public interface Dao<E> {


    /**
     * Method saves object to the database
     * @param e Model
     */
    void save(E e);

    /**
     * Retrieves a object from database based
     * on primary key e.g ID
     * @param id Primary Key
     * @return Model Object
     */
    E get(int id);

    /**
     * Method updates an existing object in the DB,
     * with altered version of the object.
     * @param e Model
     */
    void update(E e);

    /**
     * Removes the object from the DB
     * @param e Model
     */
    void delete(E e);
}
