package study.jdnc7.homeworkproject.domain.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import study.jdnc7.homeworkproject.domain.PageInfo;
import study.jdnc7.homeworkproject.domain.board.model.Board;
import study.jdnc7.homeworkproject.feature.board.model.BoardDto;

import java.util.List;

@Mapper
public interface BoardMapper {
    public void insert(Board board);
    public void update(Board board);
    public void unVisible(Long id);
    public void delete(Long id);
    public List<BoardDto.ListItem> findByPageable(PageInfo pageInfo);
    public List<BoardDto.Detail> findById(Long id);

    public boolean existById(Long id);
}