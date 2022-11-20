package study.jdnc7.homeworkproject.feature.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.jdnc7.homeworkproject.domain.File.model.File;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BoardRequest {
    private String boardTitle;
    private String boardContent;
    private List<File> boardFiles;
}
