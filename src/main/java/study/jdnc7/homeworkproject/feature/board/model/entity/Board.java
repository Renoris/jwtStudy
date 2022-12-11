package study.jdnc7.homeworkproject.feature.board.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import study.jdnc7.homeworkproject.feature.common.model.Base;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Alias("Board")
public class Board extends Base {
    private Long boardID;
    private String boardTitle;
    private String boardContent;
    private boolean visibility;

    public Board (String title, String content, boolean visibility, Long userId) {
        this.boardTitle = title;
        this.boardContent = content;
        this.visibility = visibility;
        this.createdBy = userId;
        this.modifiedBy = userId;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void updateContent(Long userId, UpdateBoard updateBoard) {
        this.boardTitle = updateBoard.getBoardTitle();
        this.boardContent = updateBoard.getBoardContent();
        this.modifiedBy = userId;
        this.modifiedAt = LocalDateTime.now();
    }
}