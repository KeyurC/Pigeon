package DAO.DAOImpl;

import DAO.IDAO.Dao;
import DAO.IDAO.IUserDao;
import Model.User;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements Dao<User>, IUserDao {
    private Logger logger = Logger.getAnonymousLogger();

    @Override
    public void save(Model.User e) {
        User user = getBasedOnUsername(e.getUser_name());

        if (user == null) {
            session.save(e);
            Transaction transaction = session.beginTransaction();
            transaction.commit();
        } else {
            logger.log(Level.INFO, "User already exists");
        }
    }

    @Override
    public Model.User get(int userID) {
        return (Model.User) session.get(User.class,userID);

    }

    @Override
    public Model.User getBasedOnUsername(String username) {
        try {
            User user = (User) session.createNativeQuery(
                    "SELECT * FROM User WHERE userName = '"+ username+"'",
                    User.class
            ).getSingleResult();

            return user;
        } catch (NoResultException e) {
            logger.log(Level.INFO, "User does not exist");
            return null;
        }



    }

    @Override
    public void update(Model.User e) {

    }

    @Override
    public void delete(Model.User e) {
        session.delete(e);
        Transaction transaction = session.beginTransaction();
        transaction.commit();
    }
}
