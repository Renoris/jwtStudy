package study.jdnc7.homeworkproject.feature.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Alias("BoardCommentDto")
public class BoardCommentDto {
    private Long commentId;
    private String commentContent;
    private LocalDateTime createdAt;
    private Long createdByName;
}
