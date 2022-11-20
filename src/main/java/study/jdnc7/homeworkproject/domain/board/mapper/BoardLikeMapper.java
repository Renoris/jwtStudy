package study.jdnc7.homeworkproject.domain.board.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardLikeMapper {
    public void insert(Long userId, Long boardId);
    public void delete(Long boardId);
}
