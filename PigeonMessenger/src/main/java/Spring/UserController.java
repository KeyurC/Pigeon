package Spring;

import Impl.UserImpl;
import Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @GetMapping(value = "/")
    public String index(Model model){
        model.addAttribute("User",new User());
        return "Login";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    @ResponseBody
    public String userCreation(@ModelAttribute User user) {
        System.out.println(" createUserMethod " + user.getUser_name());
        UserImpl.createUser(user);
        return user.getUser_name();
    }
}
