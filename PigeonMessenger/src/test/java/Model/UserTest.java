package Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(UserTest.class);
    private static User user = new User();
    private static String username = "TestUserModel";

    @BeforeAll
    static void init() {
        user.setUser_name(username);
    }

    @Test
    void usernameToJsonTest() {
        String userJson = user.usernameToJson();
        ObjectMapper mapper = new ObjectMapper();
        try {
            User user = mapper.readValue(userJson,User.class);
            assertEquals(user.getUser_name(),username,"Username retrieved from JsonObject differs from inital username");
        } catch (Exception e) {
            logger.error("usernameToJsonTest: failed to map JsonObject to User object", e);
        }
    }

}