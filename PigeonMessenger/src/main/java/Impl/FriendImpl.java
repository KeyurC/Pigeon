package Impl;

import DAO.IDAO.Dao;
import DAO.DAOImpl.FriendsWithDao;
import DAO.DAOImpl.UserDao;
import Model.FriendsWith;
import Model.User;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FriendImpl {
    static Logger logger = Logger.getAnonymousLogger();

    /**
     * Method instantiates new FriendsWith object, with
     * user and friend id and saves it to the database.
     * @param userID PK of User (int)
     * @param friendID PK of User (int)
     */
    public static void sendRequest(int userID, int friendID) {
        Dao dao = new FriendsWithDao();
        FriendsWith request = new FriendsWith();

        UserDao userDao = new UserDao();
        User user = userDao.get(userID);
        User friend = userDao.get(friendID);

        if (user != null & friend != null) {
            request.setUserID(userID);
            request.setFriendID(friendID);
            request.setFriendStatus(0);
            dao.save(request);
        } else {
            logger.log(Level.FINEST, "User does not exist, so friendship cannot be formed");
        }

    }

    /**
     * Updates FriendsWith object, which represent the friend request
     * to confirm friendship.
     * @param userID PK of User (int)
     * @param friendID PK of User (int)
     */
    public static void acceptRequest(int userID, int friendID) {
        FriendsWithDao dao = new FriendsWithDao();
        FriendsWith request = dao.getByFriends(userID,friendID);
        request.setFriendStatus(1);
        dao.update(request);

    }
}
