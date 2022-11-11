package study.jdnc7.homeworkproject.feature.user.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.jdnc7.homeworkproject.domain.user.model.User;
import study.jdnc7.homeworkproject.feature.user.model.UserRequest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void 데이터기본검색이_선행되어야한다() {
        //given
        Long id = 1L;

        //when
        User user = userMapper.findById(id);

        //then
        assertThat(user.getUserId()).isEqualTo(1L);
    }

    @Test
    public void 데이터저장시_성공적으로_저장이되어야한다 () {
        //given
        UserRequest userRequest = UserRequest.builder().userName("test1").nickName("test2").password("1234").build();
        User user = userRequest.toEntity();

        //when
        userMapper.insert(user);

        //then
        assertThat(user.getUserId()).isEqualTo(3L);
    }

    @Test
    public void 유저_비활성화시_activate가_false이_되어야한다() {
        //given
        Long id = 2L;
        User beforeUser = userMapper.findById(id);

        //when
        userMapper.deactivate(id);
        User afterUser = userMapper.findById(id);

        //then
        assertThat(beforeUser.isActivated()).isTrue();
        assertThat(afterUser.isActivated()).isFalse();
    }

    @Test
    public void 유저_삭제시_삭제가_되어야_한다() {
        //given
        Long id = 2L;
        User beforeUser = userMapper.findById(id);

        //when
        userMapper.delete(id);
        User afterUser = userMapper.findById(id);

        //then
        assertThat(beforeUser).isNotNull();
        assertThat(afterUser).isNull();
    }
}
