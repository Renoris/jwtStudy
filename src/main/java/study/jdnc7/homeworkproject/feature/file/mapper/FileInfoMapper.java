package study.jdnc7.homeworkproject.feature.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.jdnc7.homeworkproject.feature.file.model.entity.FileInfo;

@Mapper
public interface FileInfoMapper {
    public void insert(FileInfo fileInfo);
}
