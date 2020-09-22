package DAO.IDAO;

import Model.FriendsWith;

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
}
