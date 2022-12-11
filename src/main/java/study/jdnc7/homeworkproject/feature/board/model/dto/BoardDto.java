package study.jdnc7.homeworkproject.feature.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Setter
@Getter
@Alias("BoardDto")
public class BoardDto {

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListItem {
        private Long boardId;
        private String boardTitle;
        private LocalDateTime createdAt;
        private Long createdName;
        private LocalDateTime modifiedAt;
        private Long modifiedName;

    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Detail {
        private Long boardId;
        private String boardTitle;
        private String boardContent;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private Long createdByName;
        private Long modifiedByName;
    }
}
