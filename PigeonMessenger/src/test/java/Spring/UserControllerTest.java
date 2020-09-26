package Spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.apache.log4j.Logger;

import Hibernate.HibernateUtil;
import Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final Logger logger = Logger.getLogger(UserControllerTest.class);

    @Autowired
    private UserController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void pageLoads() {
        try {
            this.mockMvc.perform(get("/"))
                    .andExpect(content().contentType("text/html;charset=UTF-8"))
                    .andExpect((view().name("Login")))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            logger.error("pageLoads: MockMVC failed to perform get on Controller",e);
        }

    }

    @Test
    public void createUser() {
        String username = "SpringTestUser";
        try {
            User user = new User();

            user.setUser_name(username);
            MockHttpServletResponse result = this.mockMvc.perform(post("/createUser")
                    .flashAttr("user", user))
                    .andReturn()
                    .getResponse();
            assertEquals(result.getContentAsString(),username);
        } catch (Exception e) {
            logger.error("createUser: MockMVC failed to post " +
                    "or retrieve content of responseBody",e);

        }

    }

}
