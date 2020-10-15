package Session;

import DAO.DAOImpl.UserDao;
import Model.User;

public class UserSession {
    private String sessionID;
    private User user;

    UserSession(){}

    UserSession(String sessionID, User user) {
        this.sessionID = sessionID;
        this.user = user;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserByName(String username) {
        UserDao userDao = new UserDao();
        this.user = userDao.getBasedOnUsername(username);
    }
}
