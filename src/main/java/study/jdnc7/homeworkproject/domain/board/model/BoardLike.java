package study.jdnc7.homeworkproject.domain.board.model;

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
@Alias("BoardLike")
public class BoardLike {
    private Long likeId;
    private Long boardId;
    private LocalDateTime createdAt;
    private Long createdBy;
}
