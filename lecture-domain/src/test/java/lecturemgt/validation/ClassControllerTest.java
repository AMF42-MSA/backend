package lecturemgt.validation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.mvc.Controller;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validURL() throws Exception {
        mockMvc.perform(post("")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"url\":\"http://www.naver.com\"}"))
                .andExpect(status().isOk());
    }


    @Test
    public void invalidURL() throws Exception {
        mockMvc.perform(post("")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"url\":\"naver\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validGithubURL() throws Exception {
        mockMvc.perform(post("")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"githubUrl\":\"https://github.com/HyeranShin\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void invalidGithubURL() throws Exception {
        mockMvc.perform(post("")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"githubUrl\":\"http://www.naver.com\"}"))
                .andExpect(status().isBadRequest());
    }	
}
