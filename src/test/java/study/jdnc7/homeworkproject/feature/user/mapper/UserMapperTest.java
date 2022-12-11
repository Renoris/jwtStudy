package study.jdnc7.homeworkproject.feature.user.mapper;

import ch.qos.logback.core.util.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.jdnc7.homeworkproject.feature.user.model.entity.Authority;
import study.jdnc7.homeworkproject.feature.user.model.entity.User;
import study.jdnc7.homeworkproject.feature.user.model.dto.UserRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    // 갑자기 이거 또 안된다.
    public void 유저_비활성화시_activate가_false이_되어야한다() {
        //given
        TimeUtil.computeStartOfNextSecond(1); //데이터가 sql에 들어가기전에 이미 불러오는 것같아 1초의 지연을 줫다.
        Long id = 2L;
        User beforeUser = userMapper.findById(id);

        //when
        userMapper.deactivate(id);
        User afterUser = userMapper.findById(id);

        //then
        assertThat(beforeUser).isNotNull(); // 이 두개의 코드를 넣지않으면
        assertThat(afterUser).isNotNull(); // 바로 아래에서 nullpointer exception이 낫음 왜그러는지 모르겟다. 왜 널포인터가 뜨는지? debug에서는 안뜨는데.
        assertThat(beforeUser.isActivated()).isTrue();
        assertThat(afterUser.isActivated()).isFalse();
    }


    @Test
    public void 데이터검색시_모든것을_불러올수_잇어야한다() {
        //given
        String userName = "admin";

        //when
        Optional<User> user = userMapper.findByUserNameWithAuthority(userName);

        //then
        assertThat(user.isPresent()).isTrue();
        assertThat(user.get().getUserName()).isEqualTo(userName);
        assertThat(user.get().getAuthorities()).contains(Authority.getAdminAuthority());

    }

    @Test
    public void 데이터저장시_성공적으로_저장이되어야한다 () {
        //given
        UserRequest userRequest = UserRequest.builder().userName("test1").nickName("test2").password("1234").build();
        Authority authority = Authority.getUserAuthority();
        User user = User.toEntity(userRequest.getUserName(),userRequest.getPassword(),userRequest.getNickName(), authority);

        //when
        userMapper.insert(user);

        //then
        assertThat(user.getUserId()).isEqualTo(3L);
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
