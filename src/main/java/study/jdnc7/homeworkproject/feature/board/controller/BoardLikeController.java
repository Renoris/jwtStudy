package study.jdnc7.homeworkproject.feature.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import study.jdnc7.homeworkproject.domain.board.mapper.BoardMapper;
import study.jdnc7.homeworkproject.feature.board.model.BoardLikeDto;
import study.jdnc7.homeworkproject.feature.board.service.BoardLikeService;

@RestController
@RequestMapping("/board/like")
@RequiredArgsConstructor
public class BoardLikeController {

    private final BoardLikeService boardLikeService;
    private final BoardMapper boardMapper;

    @GetMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public BoardLikeDto getBoardLikes(@PathVariable("boardId") Long boardId) {
        if (!boardMapper.existById(boardId)) throw new RuntimeException("해당 게시물이 존재하지 않습니다.");
        return boardLikeService.getBoardLike(boardId);
    }

    @PostMapping("/{boardId}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public void createBoardLike(@PathVariable("boardId") Long boardId) {
        if (!boardMapper.existById(boardId)) throw new RuntimeException("해당 게시물이 존재하지 않습니다.");
        boardLikeService.insert(boardId);
    }

    @DeleteMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public void deleteBoardLike(@PathVariable("boardId") Long boardId) {
        if (!boardMapper.existById(boardId)) throw new RuntimeException("해당 게시물이 존재하지 않습니다.");
        boardLikeService.delete(boardId);
    }
}
