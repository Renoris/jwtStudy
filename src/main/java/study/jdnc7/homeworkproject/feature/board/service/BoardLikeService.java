package study.jdnc7.homeworkproject.feature.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jdnc7.homeworkproject.domain.board.mapper.BoardLikeMapper;
import study.jdnc7.homeworkproject.domain.board.model.BoardLikeParameter;
import study.jdnc7.homeworkproject.feature.board.model.BoardLikeDto;

@Service
@RequiredArgsConstructor
public class BoardLikeService {
    private final BoardLikeMapper boardLikeMapper;

    @Transactional(readOnly = true)
    public BoardLikeDto getBoardLikeDto(Long boardId, Long userId) {
        return boardLikeMapper.findByParameter(new BoardLikeParameter(boardId, userId));
    }

    @Transactional
    public void insert(Long boardId, Long userId) {
        boardLikeMapper.insert(new BoardLikeParameter(boardId, userId));
    }

    @Transactional
    public void delete(Long boardId, Long userId) {
        boardLikeMapper.delete(new BoardLikeParameter(boardId, userId));
    }
}
