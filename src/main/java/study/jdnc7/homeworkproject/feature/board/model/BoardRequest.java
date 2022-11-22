package study.jdnc7.homeworkproject.feature.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.jdnc7.homeworkproject.domain.File.model.File;
import study.jdnc7.homeworkproject.domain.board.model.Board;
import study.jdnc7.homeworkproject.domain.board.model.UpdateBoard;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class BoardRequest implements UpdateBoard {
    private String boardTitle;
    private String boardContent;
    private List<File> boardFiles;

    public Board toBoardEntity(Long userId) {
        return new Board(
                null,
                boardTitle,
                boardContent,
                true
        );
    }
}
