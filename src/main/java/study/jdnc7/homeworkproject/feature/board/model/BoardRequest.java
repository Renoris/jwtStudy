package study.jdnc7.homeworkproject.feature.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import study.jdnc7.homeworkproject.domain.board.model.Board;
import study.jdnc7.homeworkproject.domain.board.model.UpdateBoard;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class BoardRequest implements UpdateBoard {
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
