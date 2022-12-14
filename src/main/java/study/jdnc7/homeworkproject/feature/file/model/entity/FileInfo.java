package study.jdnc7.homeworkproject.feature.file.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import study.jdnc7.homeworkproject.feature.common.model.Base;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Alias("FileInfo")
public class FileInfo extends Base {
    private Long fileId;
    private String fileName;
    private String fileOriginName;
    private String filePath;

    public FileInfo(String fileName, String fileOriginName, String filePath, Long userId) {
        this.createdBy = userId;
        this.modifiedBy = userId;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.fileName = fileName;
        this.fileOriginName = fileOriginName;
        this.filePath = filePath;
    }
}
