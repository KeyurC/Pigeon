package Spring;

import Utility.Log4jConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PigeonApp {
    public static void main(String[] args) {
        SpringApplication.run(PigeonApp.class);
        new Log4jConf();
    }
}
