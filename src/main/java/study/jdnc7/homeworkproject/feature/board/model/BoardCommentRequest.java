package study.jdnc7.homeworkproject.feature.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.jdnc7.homeworkproject.domain.board.model.BoardComment;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentRequest {
    private Long boardId;
    private String content;

    public BoardComment toEntityWithUserId(Long userId) {
        return new BoardComment(content, userId, boardId);
    }
}
