package study.jdnc7.homeworkproject.feature.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.feature.board.model.dto.BoardFileVo;

import java.util.List;

@Mapper
public interface BoardFileMapper {
    public void insert(List<BoardFileVo> voList);
    public void deleteAllByBoardId(Long boardId);
}
