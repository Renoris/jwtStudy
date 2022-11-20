package study.jdnc7.homeworkproject.feature.user.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.jdnc7.homeworkproject.domain.user.model.Authority;
import study.jdnc7.homeworkproject.domain.user.model.User;
import study.jdnc7.homeworkproject.domain.user.mapper.UserAuthorityMapper;
import study.jdnc7.homeworkproject.domain.user.mapper.UserMapper;
import study.jdnc7.homeworkproject.feature.user.model.UserAuthorityInsertVo;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserAuthorityMapperTest {

    @Autowired
    private UserAuthorityMapper userAuthorityMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void 유저에_권한저장시_이후_유저를_불렀을때_권한을_불러올수_있어야한다() {
        //given
        Authority authority = Authority.getAdminAuthority();
        String userName = "user2";
        User user = userMapper.findByUserNameWithAuthority(userName).get();
        UserAuthorityInsertVo userAuthorityInsertVo = new UserAuthorityInsertVo(user.getUserId(), authority.getAuthorityName());

        //when
        userAuthorityMapper.insert(userAuthorityInsertVo);
        User afterUser = userMapper.findByUserNameWithAuthority(userName).get();

        //then
        assertThat(user.getAuthorities().size()).isEqualTo(0);
        assertThat(afterUser.getAuthorities().size()).isEqualTo(1);
        assertThat(afterUser.getAuthorities()).contains(authority);
//        assertThat(afterUser.getAuthorities().contains(authority)).isTrue(); // 이코드는 안된다... 왤까?
    }
}
