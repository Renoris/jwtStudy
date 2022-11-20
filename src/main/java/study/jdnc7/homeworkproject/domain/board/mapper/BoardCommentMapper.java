package study.jdnc7.homeworkproject.domain.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.domain.board.model.BoardComment;

import java.awt.print.Pageable;

@Mapper
public interface BoardCommentMapper {
    public void insert(BoardComment boardComment);
    public void update(BoardComment boardComment);
    public void delete(Long id);
    public void findByPageable(Pageable pageable);
    public void findById(Long id);
}