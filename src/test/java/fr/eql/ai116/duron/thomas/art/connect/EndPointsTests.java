package fr.eql.ai116.duron.thomas.art.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
public class EndPointsTests {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private MockMvc mockMvc;

    private String authToken = "";

    @BeforeEach
    void createUser() throws Exception {
        logger.info("create user start.");
        MvcResult result = mockMvc.perform(post("/api/rest/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "    \"email\":\"a@b.c\"," +
                                "    \"username\":\"a\"," +
                                "    \"profilePictureLink\":\"https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png\"," +
                                "    \"password\":\"a\"," +
                                "    \"artistInfos\":null" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        authToken = "Bearer " + new ObjectMapper().readTree(response).get("accessToken").asText();
        logger.info("create user end. AuthToken: {}", authToken);
    }

    @Test
    void upgradeUser() throws Exception {
        logger.info("Upgrade user start.");
        mockMvc.perform(post("/api/rest/o/a/upgrade")
                        .header(HttpHeaders.AUTHORIZATION, authToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"displayed_name\":\"nom d'artiste stylé\", \"description\":\"je susi un artiste\", \"contact\":\"appelez moi\",\"location\":\"ici\",\"profession\":\"artiste de profession\",\"accountType\":\"SINGLE\"}"))
                        .andExpect(status().isOk())
        ;
        logger.info("Upgrade user end.");
    }
}
