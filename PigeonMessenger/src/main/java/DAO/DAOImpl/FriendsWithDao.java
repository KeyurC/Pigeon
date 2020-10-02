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
import java.math.BigInteger;
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
            logger.info("Friend relationship already exists");
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
                            ("SELECT * FROM FriendsWith where (UserID = "+userID+" and FriendID = "+friendID+") " +
                                            "or (UserID = "+friendID+" and FriendID = "+userID+");",
                                    FriendsWith.class)
                            .getSingleResult();

            return request;
        } catch (NoResultException e) {
            logger.info("Friendship pair does not exist" );
            return null;
        }
    }


    @Override
    public List<User> getAllUserFriends(int userID) {
        try {
            Session session = HibernateUtil.getSession();
            List<BigInteger> friendIDs = session.createNativeQuery
                    ("select distinct IF(UserID <> "+userID+",UserID,FriendID) " +
                            "from FriendsWith WHERE ( UserID = "+userID+" or FriendID = "+userID+") and status = 1;")
                    .getResultList();

            List<User> friendsList = new ArrayList<User>();

            if (!friendIDs.isEmpty()) {
                UserDao dao = new UserDao();
                for (int i = 0; i < friendIDs.size(); i++) {
                    User tmp1User = dao.get(friendIDs.get(i).intValue());
                    friendsList.add(tmp1User);
                }
            }

            return friendsList;
        } catch (Exception e) {
            logger.error("Failed to retrieve friendlist, may be a type error", e);
        }
        return null;
    }

    @Override
    public List<User> getFriendRequests(int userID) {
        try {
            String sql = "select * from FriendsWith where FriendID = "+userID+" and status = 0;";
            Session session = HibernateUtil.getSession();
            List<FriendsWith> requests = session.createNativeQuery(sql,FriendsWith.class).getResultList();

            List<User> users = new ArrayList<User>();
            UserDao userDao = new UserDao();

            for (FriendsWith f : requests) {
                User tmp = userDao.get(f.getUserID());
                users.add(tmp);
            }

            return users;

        } catch (Exception e) {
            logger.error("getFriendRequests: Failed to retrieve all friend requests " + e );
            return null;
        }
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
        String sql = "DELETE FROM FriendsWith WHERE ID = "+friendsWith.getId()+";";
        try {
            Session session = HibernateUtil.getSession();
            session.delete(friendsWith);
            Transaction transaction = session.beginTransaction();
            transaction.commit();
        } catch (Exception e) {
            logger.error("Delete: Failed to delete object from database",e);
        }
    }
}
