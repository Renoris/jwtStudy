package study.jdnc7.homeworkproject.feature.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import study.jdnc7.homeworkproject.feature.board.model.BoardCommentDto;
import study.jdnc7.homeworkproject.feature.board.model.BoardCommentRequest;
import study.jdnc7.homeworkproject.feature.board.service.BoardCommentService;
import study.jdnc7.homeworkproject.util.SecurityUtil;

@RestController
@RequestMapping("/board/comment")
@RequiredArgsConstructor
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<BoardCommentDto> getComments(@PageableDefault Pageable pageable, @RequestParam Long boardId){
        return boardCommentService.getComments(boardId, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createComment (@ModelAttribute BoardCommentRequest request) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("접속하지 않은 유저입니다"));
        return boardCommentService.insertComment(userId, request);
    }

    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment (@PathVariable Long commentId, @ModelAttribute BoardCommentRequest request) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("접속하지 않은 유저입니다"));
        boardCommentService.updateComment(commentId, userId, request);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment (@PathVariable Long commentId) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("접속하지 않은 유저입니다"));
        boardCommentService.deleteComment(commentId, userId);
    }
}
