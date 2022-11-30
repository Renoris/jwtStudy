package study.jdnc7.homeworkproject.domain.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.domain.board.model.BoardLikeParameter;
import study.jdnc7.homeworkproject.feature.board.model.BoardLikeDto;

@Mapper
public interface BoardLikeMapper {
    public void insert(BoardLikeParameter parameter);
    public void delete(BoardLikeParameter parameter);
    BoardLikeDto findByParameter(BoardLikeParameter parameter);
    boolean existByParameter(BoardLikeParameter parameter);
}
