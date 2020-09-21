package DAO;

import Hibernate.DBConnection;
import Model.User;
import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import javax.persistence.NoResultException;
import javax.print.attribute.standard.Severity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements Dao<User>{
    private Logger logger = Logger.getAnonymousLogger();

    @Override
    public void save(Model.User e) {
        User user = getBasedOnUsername(e.getUser_name());

        if (user == null) {
            session.save(e);
            transaction.commit();
        } else {
            logger.log(Level.INFO, "User already exists");
        }
    }

    @Override
    public Model.User get(int userID) {
        return (Model.User) session.get(User.class,userID);

    }

    public Model.User getBasedOnUsername(String username) {
        try {
            User user = (User) session.createNativeQuery(
                    "SELECT * FROM User WHERE userName = '"+ username+"'",
                    User.class
            ).getSingleResult();

            return user;
        } catch (NoResultException e) {
            logger.log(Level.INFO, "User does not exist",e);
            return null;
        }



    }

    @Override
    public void update(Model.User e) {

    }

    @Override
    public void delete(Model.User e) {
        session.delete(e);
        transaction.commit();
    }
}
