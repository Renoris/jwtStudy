package study.jdnc7.homeworkproject.feature.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import study.jdnc7.homeworkproject.feature.file.model.entity.FileInfo;

import java.util.Optional;

@Mapper
public interface FileInfoMapper {
    public void insert(FileInfo fileInfo);
    public Optional<FileInfo> findById(Long fileId);
}
