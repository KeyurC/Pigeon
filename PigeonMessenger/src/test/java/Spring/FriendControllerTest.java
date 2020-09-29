package Spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class FriendControllerTest {

    private static Logger logger = Logger.getLogger(FriendControllerTest.class);

    @Autowired
    private FriendController friendController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllFriends() {
        ObjectMapper mapper = new ObjectMapper();
        String username = "test";

        try {
            MockHttpServletResponse result = this.mockMvc.perform(get("/getAllFriends")
                    .param("username",username))
                    .andReturn()
                    .getResponse();

            String[] friends = mapper.readValue(result.getContentAsString(),String[].class);

            assertTrue(friends.length > 0
                    & friends[0].contains("test_name")
                    ,"getAllFriends: Friends returned is empty or does not contain correct friend");

        } catch (Exception e) {
            logger.error("Failed to peform get and retrieve friendslist of user",e);
        }
    }
}