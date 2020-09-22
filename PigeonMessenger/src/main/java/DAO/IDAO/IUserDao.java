package DAO.IDAO;

import Model.User;

/**
 * Interface of UserDao
 */
public interface IUserDao {

    /**
     * Method returns User Object based on the username.
     * Should be used when PK is unknown.
     * @param username Primary Key of object (int)
     * @return User Object
     */
    User getBasedOnUsername(String username);
}
