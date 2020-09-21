package DAO;

import Model.FriendsWith;
import Model.User;

import javax.persistence.NoResultException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FriendsWithDao implements Dao<FriendsWith> {
    private Logger logger = Logger.getAnonymousLogger();

    @Override
    public void save(FriendsWith e) {
        session.save(e);
        transaction.commit();
    }

    @Override
    public FriendsWith get(int id) {
        return session.get(FriendsWith.class,id);
    }

    public FriendsWith getByFriends(int userID, int friendID) {
        try {
            FriendsWith request =
                    session.createNativeQuery
                            ("SELECT * FROM FriendsWith where UserID = "+userID+" and FriendID = "+friendID+"; ",
                                    FriendsWith.class)
                            .getSingleResult();

            return request;
        } catch (NoResultException e) {
            logger.log(Level.FINEST, "Friendship pair do not exist",e);
            return null;
        }
    }

    @Override
    public void update(FriendsWith friend) {
        session.update(friend);
        transaction.commit();
    }

    @Override
    public void delete(FriendsWith friendsWith) {

    }
}
