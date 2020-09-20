package DAO;

import Hibernate.DBConnection;

public class UserDao implements Dao<Model.User>{

    @Override
    public void save(Model.User e) {
        session.save(e);
        transaction.commit();
    }

    public Model.User get(int userID) {
        return (Model.User) session.get(Model.User.class,userID);

    }

    @Override
    public void update(Model.User e) {

    }

    @Override
    public void delete(Model.User e) {

    }
}
