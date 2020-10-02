package DAO.DAOImpl;

import DAO.IDAO.Dao;
import DAO.IDAO.IUserDao;
import Hibernate.HibernateUtil;
import Model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;


public class UserDao implements Dao<User>, IUserDao {
    private Logger logger = Logger.getLogger(UserDao.class);

    @Override
    public void save(Model.User e) {
        User user = getBasedOnUsername(e.getUser_name());

        if (user == null) {
            Session session = HibernateUtil.getSession();
            session.save(e);
            Transaction transaction = session.beginTransaction();
            transaction.commit();
        } else {
            logger.warn("User already exists");
        }
    }

    @Override
    public Model.User get(int userID) {
        Session session = HibernateUtil.getSession();
        return (Model.User) session.get(User.class,userID);

    }

    @Override
    public Model.User getBasedOnUsername(String username) {
        try {
            Session session = HibernateUtil.getSession();
            User user = (User) session.createNativeQuery(
                    "SELECT * FROM User WHERE userName = '"+ username+"'",
                    User.class
            ).getSingleResult();

            return user;
        } catch (NoResultException e) {
            logger.warn("User does not exist");
            return null;
        }

    }

    @Override
    public List<User> getUserList(String username) {
        try {
            Session session = HibernateUtil.getSession();
            List<User> similarUsernames = session.createNativeQuery(
                    "select * from User where userName like '%"+username+"%';"
            ,User.class).getResultList();

            return similarUsernames;
        } catch (NoResultException e) {
            logger.error("User does not exist or no similar users" ,e);
            return null;
        }
    }

    @Override
    public void update(Model.User e) {

    }

    @Override
    public void delete(Model.User e) {
        Session session = HibernateUtil.getSession();
        session.delete(e);
        Transaction transaction = session.beginTransaction();
        transaction.commit();
    }
}
