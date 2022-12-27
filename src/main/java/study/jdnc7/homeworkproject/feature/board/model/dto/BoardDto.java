package study.jdnc7.homeworkproject.feature.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import study.jdnc7.homeworkproject.feature.file.model.entity.FileInfo;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class BoardDto {

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListItem {
        private Long boardId;
        private String boardTitle;
        private LocalDateTime createdAt;
        private String createdName;
        private LocalDateTime modifiedAt;
        private String modifiedName;

    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Detail {
        private Long boardId;
        private String boardTitle;
        private String boardContent;
        private List<FileInfo> fileInfoList;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private String createdName;
        private String modifiedName;
    }
}
