package study.jdnc7.homeworkproject.domain.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Alias("BoardLikeInsertParameter")
public class BoardLikeParameter {
    private Long boardId;
    private Long userId;
}
