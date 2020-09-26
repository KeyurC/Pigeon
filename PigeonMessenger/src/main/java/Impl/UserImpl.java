package Impl;

import DAO.IDAO.Dao;
import DAO.DAOImpl.UserDao;
import Model.User;

public class UserImpl {

    /**
     * Creates a new User object and saves the user to
     * the database using the DAO.
     * @param user User Model object
     */
    public static void createUser(User user) {
        Dao userDao = new UserDao();
        userDao.save(user);


    }

    static void deleteUser() {

    }

}
