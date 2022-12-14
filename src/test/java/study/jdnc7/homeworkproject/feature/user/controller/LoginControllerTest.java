package study.jdnc7.homeworkproject.feature.user.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public void 로그인컨트롤러에접근했을때_401_인증오류를_받아야한다() throws Exception {
        //given
        String url = "/api/login";

        //when
        ResultActions rs = mockMvc.perform(get(url));

        //then
        rs.andExpect(status().isUnauthorized());
    }

    @Test
    public void 허용된_로그인_컨트롤러에_접근했을때_200_status를받을수_있어야한다() throws Exception {
        //given
        String url = "/api/login";

        //when
        ResultActions rs = mockMvc.perform(get(url));

        //then
        rs.andExpect(status().isOk());
    }
}
