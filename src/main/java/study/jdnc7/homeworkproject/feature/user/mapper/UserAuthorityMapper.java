package study.jdnc7.homeworkproject.feature.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.feature.user.model.dto.UserAuthorityInsertVo;

@Mapper
public interface UserAuthorityMapper {
    public void insert(UserAuthorityInsertVo vo);
}
