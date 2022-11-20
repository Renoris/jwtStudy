package study.jdnc7.homeworkproject.domain.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import study.jdnc7.homeworkproject.domain.Base;

@Getter
@Setter
@Alias("BoardComment")
public class BoardComment extends Base {
    private Long commentId;
    private String commentContent;
    private Long boardId;
}
