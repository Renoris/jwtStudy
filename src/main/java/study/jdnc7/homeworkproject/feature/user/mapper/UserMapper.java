package study.jdnc7.homeworkproject.feature.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.domain.user.model.User;
import study.jdnc7.homeworkproject.feature.user.model.UserDto;

import java.util.Optional;

@Mapper
public interface UserMapper {
    public User findById(Long id);

    public Optional<User> findByUserNameWithAuthority(String userName);
    public UserDto findByIdToUserDto(Long id);
    public void insert (User user);
    public void deactivate (Long id);
    public void delete(Long id);
}