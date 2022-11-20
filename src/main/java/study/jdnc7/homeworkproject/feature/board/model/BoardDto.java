package study.jdnc7.homeworkproject.feature.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

public class BoardDto {

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Alias("BoardListItem")
    public static class ListItem {
        private Long boardId;
        private String boardTitle;
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
    }
}
