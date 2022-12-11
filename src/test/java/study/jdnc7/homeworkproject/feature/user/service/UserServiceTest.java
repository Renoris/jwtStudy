package study.jdnc7.homeworkproject.feature.user.service;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import study.jdnc7.homeworkproject.feature.user.model.entity.Authority;
import study.jdnc7.homeworkproject.feature.user.model.entity.User;
import study.jdnc7.homeworkproject.feature.user.mapper.UserAuthorityMapper;
import study.jdnc7.homeworkproject.feature.user.mapper.UserMapper;
import study.jdnc7.homeworkproject.feature.user.model.dto.UserRequest;
import study.jdnc7.homeworkproject.util.SecurityUtil;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserAuthorityMapper userAuthorityMapper;


    @Test
    public void 회원가입시_회원가입이_되어있는_유저면_승인되지_말아야한다() {
        //given
        UserRequest userRequest = UserRequest.builder().userName("user").nickName("user").password("1234").build();
        User user = new User();
        given(userMapper.findByUserNameWithAuthority(userRequest.getUserName())).willReturn(Optional.of(user));

        //when//then
        assertThrows(RuntimeException.class, () -> userService.signup(userRequest));
    }


    @Test
    public void 회원가입시_정해진_Mapper의_Method를_수행해야한다() {
        //given
        Long userId = 1L;
        UserRequest userRequest = UserRequest.builder().userName("user").nickName("user").password("1234").build();
        given(userMapper.findByUserNameWithAuthority(userRequest.getUserName())).willReturn(Optional.empty());
        given(passwordEncoder.encode(userRequest.getPassword())).willReturn(userRequest.getPassword());
        doAnswer(invocation -> {
            User user = (User) invocation.getArgument(0); //invoation에서 argument로 0 - user를 가져와서 해당 user에 set
            user.setUserId(userId);
            return null;
        }).when(userMapper).insert(any(User.class));

        //when
        userService.signup(userRequest);

        //then
        verify(userMapper, times(1)).findByUserNameWithAuthority(userRequest.getUserName());
        verify(userMapper, times(1)).insert(any());
        verify(userAuthorityMapper, times(1)).insert(any());
    }

    @Test
    public void 내정보조회시_내정보를_돌려주어야한다() {
        //given
        String userName = "user";
        User user = User.toEntity(userName, "1234", userName, Authority.getUserAuthority());
        try (MockedStatic<SecurityUtil> utilities = Mockito.mockStatic(SecurityUtil.class)) {
            utilities.when(SecurityUtil::getCurrentUsername).thenReturn(Optional.of(userName));
            given(userMapper.findByUserNameWithAuthority(userName)).willReturn(Optional.of(user));
            
            //when
            Optional<User> getUser = userService.getUserWithAuthorities(userName);

            //then
            assertThat(getUser).isPresent();
            assertThat(getUser.get().getUserName()).isEqualTo(userName);
        }
    }

    @Test
    public void 유저이름으로_정보조회시_정보를_돌려주어야한다() {
        //given
        String userName = "user";
        User user = User.toEntity(userName, "1234", userName, Authority.getUserAuthority());
        given(userMapper.findByUserNameWithAuthority(userName)).willReturn(Optional.of(user));

        //when
        Optional<User> getUser = userService.getUserWithAuthorities(userName);

        //then
        assertThat(getUser).isPresent();
        assertThat(getUser.get().getUserName()).isEqualTo(userName);
    }
}

