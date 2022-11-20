package study.jdnc7.homeworkproject.domain.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.domain.board.model.Board;

import java.awt.print.Pageable;

@Mapper
public interface BoardMapper {
    public void insert(Board board);
    public void update(Board board);
    public void unVisible(Long id);
    public void delete(Long id);
    public void findByPageable(Pageable pageable);
    public void findById(Long id);
}