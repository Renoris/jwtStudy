package study.jdnc7.homeworkproject.feature.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;
import study.jdnc7.homeworkproject.domain.user.model.User;
import study.jdnc7.homeworkproject.feature.user.model.UserAuthorityInsertVo;

@Mapper
public interface UserAuthorityMapper {
    public void insert(UserAuthorityInsertVo vo);
}
