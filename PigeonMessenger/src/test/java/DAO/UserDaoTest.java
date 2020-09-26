package DAO;

import DAO.DAOImpl.UserDao;
import Hibernate.HibernateUtil;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTest {
    static UserDao userDao;

    @BeforeAll
    public static void init() {
        userDao = new UserDao();
    }


    @Test
    @Order(1)
    void save() {
        Model.User user = new Model.User();
        user.setUser_name("test");
        assertDoesNotThrow(() -> userDao.save(user));
    }

    @Test
    @Order(2)
    void getByUsernameForInvalidInput() {
        Model.User user = userDao.getBasedOnUsername("fail");
        assertNull(user,"Object returned is null if user is not present," +
                "hence test passed");
    }

    @Test
    @Order(3)
    void getByUsername() {
        Model.User user = userDao.getBasedOnUsername("test");
        assertNotNull(user,"Object returned is null if user is not present," +
                "hence test passed");
    }



    @Test
    @Order(4)
    void get() {
        Model.User user = userDao.get(1);
        String name = user.getUser_name();
        assertEquals(name,"test","Object with PK 2 has the Username" +
                " test, so if user has this name, test passed");
    }

    @Test
    @Order(5)
    void update() {
    }

    @Test
    @Order(6)
    void delete() {
    }
}