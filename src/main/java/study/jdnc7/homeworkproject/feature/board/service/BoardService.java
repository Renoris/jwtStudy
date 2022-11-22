package study.jdnc7.homeworkproject.feature.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jdnc7.homeworkproject.domain.PageInfo;
import study.jdnc7.homeworkproject.domain.board.mapper.BoardMapper;
import study.jdnc7.homeworkproject.feature.board.model.BoardDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    @Transactional(readOnly = true)
    public List<BoardDto.ListItem> getBoardList(PageInfo pageInfo) {
        return boardMapper.findByPageable(pageInfo);
    }
}
