package study.jdnc7.homeworkproject.feature.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jdnc7.homeworkproject.domain.board.mapper.BoardLikeMapper;
import study.jdnc7.homeworkproject.feature.board.model.BoardLikeDto;
import study.jdnc7.homeworkproject.util.SecurityUtil;

@Service
@RequiredArgsConstructor
public class BoardLikeService {
    private final BoardLikeMapper boardLikeMapper;

    @Transactional(readOnly = true)
    public BoardLikeDto getBoardLike(Long boardId) {
        Long userId = SecurityUtil.getCurrentUserId().orElseGet(() -> 0L);
        return boardLikeMapper.findByBoardId(boardId, userId);
    }

    @Transactional
    public void insert(Long boardId) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다."));
        boardLikeMapper.insert(userId, boardId);
    }

    @Transactional
    public void delete(Long boardId) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다."));
        boardLikeMapper.delete(userId, boardId);
    }
}
