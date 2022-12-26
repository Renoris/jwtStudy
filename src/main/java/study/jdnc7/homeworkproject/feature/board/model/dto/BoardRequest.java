package study.jdnc7.homeworkproject.feature.board.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import study.jdnc7.homeworkproject.feature.board.model.entity.Board;
import study.jdnc7.homeworkproject.feature.board.model.entity.UpdateBoard;
import study.jdnc7.homeworkproject.feature.file.model.entity.UploadFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class BoardRequest implements UpdateBoard, UploadFile {
    private String boardTitle;
    private String boardContent;
    private boolean visibility;
    private List<MultipartFile> files;

    public Board toBoardEntity(Long userId) {
        return new Board(
                null,
                boardTitle,
                boardContent,
                true
        );
    }
}
