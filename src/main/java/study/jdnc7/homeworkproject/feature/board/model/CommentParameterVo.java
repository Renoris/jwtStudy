package study.jdnc7.homeworkproject.feature.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import study.jdnc7.homeworkproject.domain.PageInfo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Alias("CommentParameterVo")
public class CommentParameterVo {
    private Long boardId;
    private PageInfo pageInfo;
}
