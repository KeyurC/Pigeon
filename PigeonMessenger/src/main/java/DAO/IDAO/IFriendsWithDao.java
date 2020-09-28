package DAO.IDAO;

import Model.FriendsWith;
import Model.User;

import java.util.List;

/**
 * FriendsWith interface, which is made for the FriendsWithDao
 */
public interface IFriendsWithDao {

    /**
     * Method returns the object using UserID
     * and FriendID as identifiers.
     * Should be used when PK is not known.
     * @param userID Identifier of user
     * @param friendID Identifier of user
     * @return FriendsWith object
     */
    FriendsWith getByFriends(int userID, int friendID);

    /**
     * Method returns a List of User objects, which
     * are friends with the userID passed in
     * @param userID ID of User Object
     * @return List of User
     */
    List<User> getAllUserFriends(int userID);
}
