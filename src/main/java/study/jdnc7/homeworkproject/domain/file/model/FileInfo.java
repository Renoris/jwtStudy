package study.jdnc7.homeworkproject.domain.file.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import study.jdnc7.homeworkproject.domain.Base;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Alias("File")
public class FileInfo extends Base {
    private Long fileId;
    private String fileName;
    private String fileOriginName;
    private String filePath;

    public FileInfo(Long userId, String fileName, String fileOriginName, String filePath) {
        this.createdBy = userId;
        this.modifiedBy = userId;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.fileName = fileName;
        this.fileOriginName = fileOriginName;
        this.filePath = filePath;
    }
}
