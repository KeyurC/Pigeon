package Spring;

import DAO.DAOImpl.FriendsWithDao;
import DAO.DAOImpl.UserDao;
import Impl.UserImpl;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @GetMapping(value = "/")
    public String index(Model model){
        model.addAttribute("User",new User());
        return "Login";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    @ResponseBody
    public String userCreation(@RequestBody String userJSON) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            User user = mapper.readValue(userJSON,User.class);
            UserImpl.createUser(user);
            return "Success";
        } catch (Exception e) {
            logger.error("userCreation: failed to map JsonObject to User object", e);
            return null;
        }


    }

    @RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<String> searchUsers(@RequestParam String username, String friend) {

        try {
            FriendsWithDao friendsWithDao = new FriendsWithDao();
            UserDao userDao = new UserDao();

            User user = userDao.getBasedOnUsername(username);
            List<User> friends = friendsWithDao.getAllUserFriends(user.getUser_id());

            List<String> friendsNameList = new ArrayList<String>();
            List<User> userList = userDao.getUserList(friend);

            for (User u: friends) {
                friendsNameList.add(u.getUser_name());
            }

            List<String> allSimilarUsers = new ArrayList<String>();

            for (User s: userList) {
                if (!username.equals(s.getUser_name())) {
                    JsonObject json = new JsonObject();
                    json.addProperty("id",s.getUser_id());
                    json.addProperty("name",s.getUser_name());
                    if (friendsNameList.contains(s.getUser_name())) {
                        json.addProperty("isFriend",1);
                    } else {
                        json.addProperty("isFriend",0);
                    }
                    allSimilarUsers.add(json.toString());
                }
            }


            return allSimilarUsers;
        } catch (Exception e) {
            logger.error("userCreation: failed to map JsonObject to User object", e);
            return null;
        }


    }
}
