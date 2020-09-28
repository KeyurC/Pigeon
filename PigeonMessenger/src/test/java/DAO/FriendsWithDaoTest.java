package DAO;

import DAO.DAOImpl.FriendsWithDao;
import DAO.DAOImpl.UserDao;
import Hibernate.HibernateUtil;
import Model.FriendsWith;
import Model.User;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FriendsWithDaoTest {

    @BeforeAll
    static void setUp() throws InterruptedException {

        User user = new User();
        User user1 = new User();
        UserDao userDao = new UserDao();

        if (userDao.get(1) == null) {
            user.setUser_name("test");
            userDao.save(user);
        }
        if (userDao.get(2) == null) {
            user1.setUser_id(2);
            user1.setUser_name("test_name");
            userDao.save(user1);
        }

    }

    @Test
    @Order(1)
    void save() {
        FriendsWithDao dao = new FriendsWithDao();
        FriendsWith request = new FriendsWith();

        request.setUserID(1);
        request.setFriendID(2);
        request.setFriendStatus(0);
        assertDoesNotThrow(() -> dao.save(request),
                "No throw means object saved successfully");
    }

    @Test
    @Order(2)
    void get() {
    }

    @Order(3)
    @Test
    void getByFriends() {
        FriendsWithDao dao = new FriendsWithDao();
        FriendsWith friend = dao.getByFriends(1,2);
        int userID = friend.getUserID();
        assertEquals(userID,1,
                "Pass means integrity of object is confirmed");
    }


    @Test
    @Order(4)
    void update() {
        FriendsWithDao dao = new FriendsWithDao();
        FriendsWith friend = dao.get(1);
        friend.setFriendStatus(1);
        dao.update(friend);

        friend = dao.get(1);
        System.out.println(friend.getUserID());
        assertEquals(friend.getFriendStatus(),1);

    }

    @Order(5)
    @Test
    void delete() {
    }

    @Order(6)
    @Test
    void getAllFriends() {
        FriendsWithDao dao = new FriendsWithDao();
        List<User> friendsList = dao.getAllUserFriends(1);
        User tmpUser = friendsList.get(0);
        assertEquals(tmpUser.getUser_name(), "test_name","getAllFriends: Username retrieved from friendslist does not match friend assigned");

    }
}