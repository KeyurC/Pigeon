package DAO.IDAO;

import DAO.IDAO.Dao;
import DAO.IDAO.IFriendsWithDao;
import Model.FriendsWith;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FriendsWithDao implements Dao<FriendsWith>, IFriendsWithDao {
    private Logger logger = Logger.getAnonymousLogger();

    @Override
    public void save(FriendsWith e) {
        int userID = e.getUserID();
        int friendID = e.getFriendID();
        FriendsWith result = getByFriends(userID,friendID);
        if (result == null) {
            try {
                session.save(e);
                Transaction transaction = session.beginTransaction();
                transaction.commit();
            } catch (Exception ex) {
                logger.log(Level.SEVERE,"Failed to save friend relationship",ex);
            };
        } else {
            logger.log(Level.FINEST,"Friend relationship already exists");
        }
    }

    @Override
    public FriendsWith get(int id) {
        return session.get(FriendsWith.class,id);
    }

    @Override
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
        Transaction transaction = session.beginTransaction();
        transaction.commit();
    }

    @Override
    public void delete(FriendsWith friendsWith) {

    }
}
