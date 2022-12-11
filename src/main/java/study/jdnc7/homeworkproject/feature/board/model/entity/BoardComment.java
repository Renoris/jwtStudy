package study.jdnc7.homeworkproject.feature.board.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import study.jdnc7.homeworkproject.feature.common.model.Base;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Alias("BoardComment")
public class BoardComment extends Base {
    private Long commentId;
    private String commentContent;
    private Long boardId;

    public BoardComment (String content, Long userId, Long boardId) {
        this.commentContent = content;
        this.boardId = boardId;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.createdBy = userId;
        this.modifiedBy = userId;
    }

    public void update(String content, Long userId) {
        this.commentContent = content;
        this.modifiedAt = LocalDateTime.now();
        this.modifiedBy = userId;
    }
}
