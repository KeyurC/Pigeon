package DAO;

import Hibernate.DBConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    static UserDao userDao;

    @BeforeAll
    public static void init() {
        DBConnection con = new DBConnection();
        userDao = new UserDao();
    }

    @Test
    void save() {
        Model.User user = new Model.User();
        user.setUser_name("test");
        assertDoesNotThrow(() -> userDao.save(user));
    }

    @Test
    void get() {
        Model.User user = userDao.get(4);
        String name = user.getUser_name();
        assertEquals(name,"test");
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}