package study.jdnc7.homeworkproject.feature.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.domain.user.model.User;

@Mapper
public interface AuthorityUserMapper {
    public void insert(User user);
}
