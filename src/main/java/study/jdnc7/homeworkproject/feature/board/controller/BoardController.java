package study.jdnc7.homeworkproject.feature.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import study.jdnc7.homeworkproject.feature.board.model.dto.BoardDto;
import study.jdnc7.homeworkproject.feature.board.model.dto.BoardRequest;
import study.jdnc7.homeworkproject.feature.board.service.BoardService;
import study.jdnc7.homeworkproject.util.SecurityUtil;

import java.io.IOException;


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
    public Long createBoard(@ModelAttribute BoardRequest boardRequest) throws IOException {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("접속하지 않은 유저입니다"));
        return boardService.createBoard(userId, boardRequest);
    }

    @PatchMapping("/{boardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBoard(@PathVariable Long boardId, @ModelAttribute BoardRequest boardRequest) throws IOException {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("접속하지 않은 유저입니다"));
        boardService.updateBoard(userId, boardId, boardRequest);
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
