package study.jdnc7.homeworkproject.feature.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import study.jdnc7.homeworkproject.domain.board.model.Board;

import java.time.LocalDateTime;

public class BoardDto {

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Alias("BoardListItem")
    public static class ListItem {
        private Long boardId;
        private String boardTitle;
        private LocalDateTime createdAt;
        private Long createdByName;
        private LocalDateTime modifiedAt;
        private Long modifiedByName;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Alias("BoardDetail")
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
