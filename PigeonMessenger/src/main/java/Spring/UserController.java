package Spring;

import Impl.UserImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/createUser")
    public String test(@RequestParam(value = "name",defaultValue = "") String name) {
        UserImpl.createUser(name);
        return name;
    }
}
