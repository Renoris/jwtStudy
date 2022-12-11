package study.jdnc7.homeworkproject.feature.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.feature.board.model.entity.BoardComment;
import study.jdnc7.homeworkproject.feature.board.model.dto.BoardCommentDto;
import study.jdnc7.homeworkproject.feature.board.model.dto.CommentParameterVo;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardCommentMapper {
    public void insert(BoardComment boardComment);
    public void update(BoardComment boardComment);
    public void delete(Long id);
    public List<BoardCommentDto> findByParameterVo(CommentParameterVo vo);
    public Optional<BoardComment> findById(Long id);
    public long findTotalCount();
}