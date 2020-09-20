package Spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringController {

    @GetMapping("/test")
    public String test(@RequestParam(value = "name",defaultValue = "fail") String name) {
        return name;
    }

}
