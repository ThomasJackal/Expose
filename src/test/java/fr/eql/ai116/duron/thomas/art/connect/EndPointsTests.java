package fr.eql.ai116.duron.thomas.art.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    private MockMvc mockMvc;

    private String authToken = "";

    @BeforeEach
    void createUser() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/rest/security/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"a\",\"password\":\"a\",\"roleName\":\"USER\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        authToken = "Bearer " + new ObjectMapper().readTree(response).get("accessToken").asText();
    }

    @Test
    void upgradeUser() throws Exception {
        mockMvc.perform(post("/api/rest/o/a/upgrade")
                        .header(HttpHeaders.AUTHORIZATION, authToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"displayed_name\":\"nom d'artiste stylé\", \"description\":\"je susi un artiste\", \"contact\":\"appelez moi\",\"location\":\"ici\",\"profession\":\"artiste de profession\",\"accountType\":\"SINGLE\"}"))
                        .andExpect(status().isOk())
        ;
    }
}
