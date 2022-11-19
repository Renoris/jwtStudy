package study.jdnc7.homeworkproject.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import study.jdnc7.homeworkproject.feature.user.model.LoginRequest;
import study.jdnc7.homeworkproject.jwt.TokenProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TokenProvider tokenProvider;

    @Test
    public void 적합한_사용자의_아이디와_패스워드를_인증했을경우_jwt를_받을수_있어야한다() throws Exception {
        //given
        String userName = "admin";
        String password = "kimbyungjun1234!@jwtprojectpassword";
        LoginRequest loginRequest = LoginRequest.builder().userName(userName).password(password).build();
        given(tokenProvider.createToken(any())).willReturn("testjwt");

        //when
        ResultActions resultActions = mockMvc.perform(post("/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)));

        //then
        resultActions.andExpect(status().isOk());
        assertThat(((String) resultActions.andReturn().getResponse().getHeaderValue("Authorization"))).isNotNull();
    }


    @Test
    public void passwordCreator() {
        String password = "kimbyungjun1234!@jwtprojectpassword";
        System.out.println(passwordEncoder.encode(password));
    }
}
