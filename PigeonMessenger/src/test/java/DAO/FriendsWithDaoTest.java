package DAO;

import Hibernate.DBConnection;
import Model.FriendsWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FriendsWithDaoTest {

    @BeforeEach
    void setUp() {
        new DBConnection();
    }


    @Test
    void save() {
//        Dao dao = new FriendsWithDao();
//        FriendsWith request = new FriendsWith();
//
//        request.setUserID(1);
//        request.setFriendID(2);
//        request.setFriendStatus(0);
//        dao.save(request);
    }

    @Test
    void get() {
    }

    @Test
    void getByFriends() {
        FriendsWithDao dao = new FriendsWithDao();
        FriendsWith friend = dao.getByFriends(3,2);
        System.out.println(friend.getUserID());
    }


    @Test
    void update() {
        FriendsWithDao dao = new FriendsWithDao();
        FriendsWith friend = dao.get(1);
        friend.setFriendStatus(1);
        dao.update(friend);

    }

    @Test
    void delete() {
    }
}