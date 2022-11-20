package study.jdnc7.homeworkproject.domain.File.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import study.jdnc7.homeworkproject.domain.Base;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Alias("File")
public class File extends Base {
    private Long fileId;
    private String fileName;
    private String fileOriginName;
    private String filePath;
}
