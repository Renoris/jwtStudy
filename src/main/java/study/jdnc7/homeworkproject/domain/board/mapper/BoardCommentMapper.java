package study.jdnc7.homeworkproject.domain.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.domain.PageInfo;
import study.jdnc7.homeworkproject.domain.board.model.BoardComment;
import study.jdnc7.homeworkproject.feature.board.model.BoardCommentDto;
import study.jdnc7.homeworkproject.feature.board.model.CommentParameterVo;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardCommentMapper {
    public void insert(BoardComment boardComment);
    public void update(BoardComment boardComment);
    public void delete(Long id);
    public List<BoardCommentDto> findByParameterVo(CommentParameterVo vo);
    public Optional<BoardComment> findById(Long id);

    public void existById(Long id);

    public long findTotalCount();
}