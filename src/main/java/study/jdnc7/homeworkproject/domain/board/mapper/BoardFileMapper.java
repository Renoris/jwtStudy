package study.jdnc7.homeworkproject.domain.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.domain.File.model.File;
import study.jdnc7.homeworkproject.feature.board.model.BoardFileVo;

import java.util.List;

@Mapper
public interface BoardFileMapper {
    public void insert(List<BoardFileVo> voList);
    public void deleteAllByBoardId(Long boardId);
}
