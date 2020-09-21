package Impl;

import DAO.Dao;
import DAO.UserDao;
import Hibernate.DBConnection;
import Model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserImplTest {
    static UserDao dao;

    @BeforeAll
    static void init() {
        new DBConnection();
        dao = new UserDao();
    }

    @Test
    void createUser() {
        String username = "test_name";
        UserImpl.createUser(username);
        User user = (User) dao.getBasedOnUsername(username);
        assertEquals(user.getUser_name(), username);
    }


    @Test
    void deleteUser() {
    }
}