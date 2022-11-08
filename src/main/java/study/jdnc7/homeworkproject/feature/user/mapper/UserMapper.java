package study.jdnc7.homeworkproject.feature.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.domain.user.model.User;
import study.jdnc7.homeworkproject.feature.user.model.UserDto;

@Mapper
public interface UserMapper {
    public UserDto findByIdToUserDto(Long id);
    public Long insert (User user);
    public void deactivate (Long id);
}