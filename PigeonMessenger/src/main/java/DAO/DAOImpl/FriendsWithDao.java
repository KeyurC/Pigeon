package DAO.DAOImpl;

import DAO.IDAO.Dao;
import DAO.IDAO.IFriendsWithDao;
import Hibernate.HibernateUtil;
import Model.FriendsWith;
import Model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class FriendsWithDao implements Dao<FriendsWith>, IFriendsWithDao {
    private static Logger logger = Logger.getLogger(FriendsWithDao.class);

    @Override
    public void save(FriendsWith e) {
        int userID = e.getUserID();
        int friendID = e.getFriendID();
        FriendsWith result = getByFriends(userID,friendID);
        if (result == null) {
            try {
                Session session = HibernateUtil.getSession();
                session.save(e);
                Transaction transaction = session.beginTransaction();
                transaction.commit();
            } catch (Exception ex) {
                logger.error("Failed to save friend relationship",ex);
            };
        } else {
            logger.warn("Friend relationship already exists");
        }
    }

    @Override
    public FriendsWith get(int id) {
        Session session = HibernateUtil.getSession();
        return session.get(FriendsWith.class,id);
    }

    @Override
    public FriendsWith getByFriends(int userID, int friendID) {
        try {
            Session session = HibernateUtil.getSession();
            FriendsWith request =
                    session.createNativeQuery
                            ("SELECT * FROM FriendsWith where UserID = "+userID+" and FriendID = "+friendID+"; ",
                                    FriendsWith.class)
                            .getSingleResult();

            return request;
        } catch (NoResultException e) {
            logger.error("Friendship pair does not exist" ,e);
            return null;
        }
    }

    @Override
    public List<User> getAllUserFriends(int userID) {
        try {
            Session session = HibernateUtil.getSession();
            List<Integer> friendIDs = session.createNativeQuery
                    ("SELECT FriendID FROM FriendsWith where UserID = "+userID+"; ")
                    .getResultList();

            List<User> friendsList = new ArrayList<User>();

            if (!friendIDs.isEmpty()) {
                UserDao dao = new UserDao();
                for (int i = 0; i < friendIDs.size(); i++) {
                    User tmpUser = dao.get(friendIDs.get(i));
                    friendsList.add(tmpUser);
                }
            }

            return friendsList;
        } catch (Exception e) {
            logger.error("Failed to retrieve friendlist, may be a type error", e);
        }
        return null;
    }

    @Override
    public void update(FriendsWith friend) {
        Session session = HibernateUtil.getSession();
        session.update(friend);
        Transaction transaction = session.beginTransaction();
        transaction.commit();
    }

    @Override
    public void delete(FriendsWith friendsWith) {

    }
}
