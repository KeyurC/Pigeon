package Spring;

import DAO.DAOImpl.FriendsWithDao;
import DAO.DAOImpl.UserDao;
import Impl.FriendImpl;
import Model.FriendsWith;
import Model.User;
import Session.UserSessionStore;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FriendController {
    private static FriendsWithDao friendsDAO = new FriendsWithDao();
    private static UserDao userDAO = new UserDao();
    private static Logger logger = Logger.getLogger(FriendController.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void updateActivityHub(String username) {
        String sessionID = UserSessionStore.getSessionID(username);

        if (sessionID != null) {
            this.simpMessagingTemplate.convertAndSend("/friend/friendsList-"+sessionID,
                    "true-"+sessionID);
        }
    }

    @MessageMapping("/register")
    public void checkFriendsList(JsonNode json) {
        String sessionID = json.get("session").asText();
        String username = json.get("username").asText();

        UserSessionStore.add(sessionID,username);
        UserSessionStore.print();
    }

    @RequestMapping(value = "/acceptRequest", method = RequestMethod.POST)
    @ResponseBody
    public void acceptRequest(@RequestBody JsonNode json) {
        JsonNode params = json.get("params");
        String username = params.get("username").textValue();
        String friendID = params.get("friendID").toString();

        User friend = userDAO.get(Integer.valueOf(friendID));

        this.updateActivityHub(friend.getUser_name());

        int userID = userDAO.getBasedOnUsername(username).getUser_id();
        FriendImpl.acceptRequest(userID,Integer.valueOf(friendID));
    }

    @RequestMapping(value = "/declineRequest", method = RequestMethod.POST)
    @ResponseBody
    public void declineRequest(@RequestBody JsonNode json) {
        JsonNode params = json.get("params");
        String username = params.get("username").textValue();
        String friendID = params.get("friendID").toString();

        FriendsWithDao friendsWithDao = new FriendsWithDao();

        int userID = userDAO.getBasedOnUsername(username).getUser_id();
        FriendsWith friend = friendsWithDao.getByFriends(userID,Integer.valueOf(friendID));
        friendsWithDao.delete(friend);
    }

    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    @ResponseBody
    public void addFriend(@RequestBody JsonNode payload) {
        JsonNode params = payload.get("params");
        String username = params.get("username").textValue();
        String friendID = params.get("friendID").toString();

        User friend = userDAO.get(Integer.valueOf(friendID));

        this.updateActivityHub(friend.getUser_name());

        User user = userDAO.getBasedOnUsername(username);
        FriendImpl.sendRequest(user.getUser_id(),Integer.valueOf(friendID));
    }

    @RequestMapping(value = "/getAllFriends", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getAllFriends(@RequestParam String username) {
        User user = userDAO.getBasedOnUsername(username);
        List<User> friendsList = friendsDAO.getAllUserFriends(user.getUser_id());

        List<String> usernameList = new ArrayList<String>();
        for (User u: friendsList) {
            StringBuilder builder = new StringBuilder();
            builder.append(u.getUser_id());
            builder.append(",");
            builder.append(u.getUser_name());
            usernameList.add(builder.toString());
        }

        return usernameList;
    }

    @RequestMapping(value = "/getFriendRequests", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getFriendRequests(@RequestParam String username) {
        User user = userDAO.getBasedOnUsername(username);
        return friendsDAO.getFriendRequests(user.getUser_id());
    }
}
