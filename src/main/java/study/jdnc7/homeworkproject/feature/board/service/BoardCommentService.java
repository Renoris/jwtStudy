package study.jdnc7.homeworkproject.feature.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jdnc7.homeworkproject.feature.common.model.PageInfo;
import study.jdnc7.homeworkproject.feature.board.mapper.BoardCommentMapper;
import study.jdnc7.homeworkproject.feature.board.mapper.BoardMapper;
import study.jdnc7.homeworkproject.feature.board.model.entity.BoardComment;
import study.jdnc7.homeworkproject.feature.board.model.dto.BoardCommentDto;
import study.jdnc7.homeworkproject.feature.board.model.dto.BoardCommentRequest;
import study.jdnc7.homeworkproject.feature.board.model.dto.CommentParameterVo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardMapper boardMapper;
    private final BoardCommentMapper boardCommentMapper;
    @Transactional(readOnly = true)
    public Page<BoardCommentDto> getComments(Long boardId, Pageable pageable) {
        PageInfo pageInfo = PageInfo.of(pageable);
        List<BoardCommentDto> list = boardCommentMapper.findByParameterVo(new CommentParameterVo(boardId, pageInfo));
        long count = boardCommentMapper.findTotalCount();
        return new PageImpl<>(list, pageable, count);
    }

    @Transactional
    public Long insertComment(Long userId, BoardCommentRequest request) {
        //라인이 읽기가 힘들다 띄어쓰기도 필요하고
        if (!boardMapper.existById(request.getBoardId())) throw new RuntimeException("해당 게시물이 존재하지 않습니다.");
        BoardComment boardComment = request.toEntityWithUserId(userId);
        boardCommentMapper.insert(boardComment);
        return boardComment.getCommentId();
    }

    @Transactional
    public void updateComment(Long commentId, Long userId, BoardCommentRequest request) {
        if (!boardMapper.existById(request.getBoardId())) throw new RuntimeException("해당 게시물이 존재하지 않습니다.");
        BoardComment boardComment = boardCommentMapper.findById(commentId).orElseThrow(() -> new RuntimeException("해당 댓글이 존재하지 않습니다."));
        if (!boardComment.getCreatedBy().equals(userId)) throw new RuntimeException("해당 댓글의 작성자가 아닙니다.");
        boardComment.update(request.getContent(), userId);
        boardCommentMapper.update(boardComment);
    }

    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        BoardComment boardComment = boardCommentMapper.findById(commentId).orElseThrow(() -> new RuntimeException("해당 댓글이 존재하지 않습니다."));
        if (!boardComment.getCreatedBy().equals(userId)) throw new RuntimeException("해당 댓글의 작성자가 아닙니다.");
        boardCommentMapper.delete(commentId);
    }
}
