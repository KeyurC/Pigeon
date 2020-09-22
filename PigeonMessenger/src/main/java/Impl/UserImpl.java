package Impl;

import DAO.IDAO.Dao;
import DAO.DAOImpl.UserDao;
import Model.User;

public class UserImpl {

    /**
     * Creates a new User object and saves the user to
     * the database using the DAO.
     * @param name
     */
    public static void createUser(String name) {
        Dao userDao = new UserDao();
        User user = new User();

        user.setUser_name(name);
        userDao.save(user);


    }

    static void deleteUser() {

    }

}
