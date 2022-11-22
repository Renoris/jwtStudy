package study.jdnc7.homeworkproject.feature.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import study.jdnc7.homeworkproject.domain.user.model.User;
import study.jdnc7.homeworkproject.feature.user.model.UserRequest;
import study.jdnc7.homeworkproject.feature.user.service.UserService;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @WithAnonymousUser
    public void 회원가입시_user의Id값을_받아올수_있어야_한다() throws Exception {
        //given
        UserRequest userRequest = new UserRequest("user4", "password1234", "user4");
        given(userService.signup(userRequest)).willReturn(3L);

        //when
        ResultActions resultActions = mockMvc.perform(post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)));

        //then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    @WithAnonymousUser
    public void 나의정보_조회시_유저정보가없으면_거부되어야한다() throws Exception {
        //given
        given(userService.getMyUserWithAuthorities()).willReturn(Optional.empty());

        //when
        ResultActions resultActions = mockMvc.perform(get("/user/my"));

        //then
        resultActions.andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER", username = "user2")
    public void 유저정보_조회시_관리자가_아니면_거부되어야한다() throws Exception {
        //given
        String userName = "user4";
        User user = new User();
        given(userService.getMyUserWithAuthorities()).willReturn(Optional.of(user));

        //when
        ResultActions resultActions = mockMvc.perform(get("/user/{userName}", userName));

        //then
        resultActions.andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN", username = "user2")
    public void 유저정보_조회시_관리자이면_조회할수_있어야_한다() throws Exception {
        //given
        String userName = "user4";
        User user = new User();
        given(userService.getUserWithAuthorities(userName)).willReturn(Optional.of(user));

        //when
        ResultActions resultActions = mockMvc.perform(get("/user/{userName}", userName));

        //then
        resultActions.andExpect(status().isOk());
    }
}
