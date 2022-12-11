package study.jdnc7.homeworkproject.feature.file.model.entity;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadFile {
    public List<MultipartFile> getFiles();
}
