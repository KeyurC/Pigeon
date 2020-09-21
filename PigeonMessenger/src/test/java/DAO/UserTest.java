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
        Model.User user = userDao.get(2);
        String name = user.getUser_name();
        assertEquals(name,"test","Object with PK 2 has the Username" +
                " test, so if user has this name, test passed");
    }

    @Test
    void getByUsername() {
        Model.User user = userDao.getBasedOnUsername("test");
        assertNotNull(user,"Object returned is null if user is not present," +
                "hence test passed");
    }


    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}