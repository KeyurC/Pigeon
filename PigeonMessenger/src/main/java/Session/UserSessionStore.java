package Session;

import DAO.DAOImpl.UserDao;
import Model.User;

import java.util.HashMap;
import java.util.Map;

public class UserSessionStore {

    private static final HashMap<String, UserSession> sessionStore = new HashMap<>();

    public static void add(String sessionID,String username) {
        UserDao userDao = new UserDao();
        User user = userDao.getBasedOnUsername(username);
        UserSession session = new UserSession(sessionID,user);

        sessionStore.put(username,session);
    }

    public static User getUser(String username) {
        UserSession session = sessionStore.get(username);
        return session.getUser();
    }

    public static String getSessionID(String username) {
        UserSession session = sessionStore.get(username);
        return session.getSessionID();
    }

    public static void print() {
        for (Map.Entry s : sessionStore.entrySet()) {
            UserSession session = (UserSession) s.getValue();
            System.out.println(s.getKey() + " " + session.getSessionID());
        }
    }

}
