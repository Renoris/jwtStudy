package study.jdnc7.homeworkproject.domain.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.jdnc7.homeworkproject.feature.board.model.BoardLikeDto;

import java.util.Optional;

@Mapper
public interface BoardLikeMapper {
    public void insert(Long userId, Long boardId);
    public void delete(Long userId, Long boardId);

    BoardLikeDto findByBoardId(@Param("boardId") Long boardId, @Param("userId") Long userId);
}
