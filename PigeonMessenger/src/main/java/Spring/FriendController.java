package Spring;

import DAO.DAOImpl.FriendsWithDao;
import DAO.DAOImpl.UserDao;
import Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FriendController {

    private static Logger logger = Logger.getLogger(FriendController.class);

    @RequestMapping(value = "/getAllFriends", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getAllFriends(@RequestParam String username) {
        UserDao userDAO = new UserDao();
        FriendsWithDao friendsDAO = new FriendsWithDao();

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
}
