package study.jdnc7.homeworkproject.feature.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.feature.common.model.PageInfo;
import study.jdnc7.homeworkproject.feature.board.model.entity.Board;
import study.jdnc7.homeworkproject.feature.board.model.dto.BoardDto;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {

    public List<BoardDto.ListItem> findAllByPageInfo(PageInfo pageInfo);
    public Optional<BoardDto.Detail> findByIdToDetail(Long id);
    public Optional<Board> findById(Long id);
    public void insert(Board board);
    public void update(Board board);
    public void unVisible(Long userId, Long boardId);
    public void delete(Long id);
    public Long findTotalCount();
    public boolean existById(Long id);
}