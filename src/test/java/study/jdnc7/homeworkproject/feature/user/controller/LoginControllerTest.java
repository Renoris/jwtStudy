package study.jdnc7.homeworkproject.feature.user.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginControllerTest.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 로그인컨트롤러에접근했을때_401_인증오류를_받아야한다() throws Exception {
        //given
        String url = "/api/login";

        //when
        ResultActions rs = mockMvc.perform(get(url));

        //then
        rs.andExpect(status().isUnauthorized());

    }
}
