package DAO.IDAO;

import Model.User;

import java.util.List;


/**
 * Interface of UserDao
 */
public interface IUserDao {

    /**
     * Method returns User Object based on the username.
     * Should be used when PK is unknown.
     *
     * @param username Primary Key of object (int)
     * @return User Object
     */
    User getBasedOnUsername(String username);

    /**
     * Method returns a list of user objects who have similar
     * usernames to the username passed in.
     * @param username
     * @return List of similar usernames
     */
    List<User> getUserList(String username);
}