package Spring;

import Impl.UserImpl;
import org.apache.log4j.Logger;
import Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
}
