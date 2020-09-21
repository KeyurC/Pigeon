package Impl;

import DAO.Dao;
import DAO.FriendsWithDao;
import Model.FriendsWith;

public class FriendImpl {
    public static void sendRequest(int userID, int friendID) {
        Dao dao = new FriendsWithDao();
        FriendsWith request = new FriendsWith();

        request.setUserID(userID);
        request.setFriendID(friendID);
        request.setFriendStatus(0);
        dao.save(request);

    }

    public static void acceptRequest(int userID, int friendID) {
        FriendsWithDao dao = new FriendsWithDao();
        FriendsWith request = dao.getByFriends(userID,friendID);
        request.setFriendStatus(1);
        dao.update(request);

    }
}
