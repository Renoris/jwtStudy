package study.jdnc7.homeworkproject.feature.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import study.jdnc7.homeworkproject.domain.PageInfo;
import study.jdnc7.homeworkproject.domain.board.mapper.BoardMapper;
import study.jdnc7.homeworkproject.feature.board.model.BoardDto;
import study.jdnc7.homeworkproject.feature.board.model.BoardRequest;
import study.jdnc7.homeworkproject.feature.board.service.BoardService;
import study.jdnc7.homeworkproject.util.SecurityUtil;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<BoardDto.ListItem> getBoardList(@PageableDefault Pageable pageable) {
        return boardService.getBoardList(pageable);
    }

    @GetMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public BoardDto.Detail getBoardDetail(@PathVariable Long boardId) {
        return boardService.getBoardDetail(boardId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createBoard(@ModelAttribute BoardRequest boardRequest) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("접속하지 않은 유저입니다"));
        return null;
    }

    @PatchMapping("/{boardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBoard(@PathVariable Long boardId, @ModelAttribute BoardRequest boardRequest) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("접속하지 않은 유저입니다"));
    }

    @PatchMapping("/unvisible/{boardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unVisibleBoard(@PathVariable Long boardId) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("접속하지 않은 유저입니다"));
        boardService.unVisible(userId, boardId);
    }

    @DeleteMapping("/{boardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long boardId) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("접속하지 않은 유저입니다"));
        boardService.delete(userId, boardId);
    }
}
