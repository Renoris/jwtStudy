package study.jdnc7.homeworkproject.domain.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.domain.file.model.FileInfo;

@Mapper
public interface FileInfoMapper {
    public void insert(FileInfo fileInfo);
}
