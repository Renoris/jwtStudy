package study.jdnc7.homeworkproject.domain.board.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import study.jdnc7.homeworkproject.domain.Base;

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
}