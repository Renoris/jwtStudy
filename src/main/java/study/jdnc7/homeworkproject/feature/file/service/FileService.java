package study.jdnc7.homeworkproject.feature.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import study.jdnc7.homeworkproject.feature.file.mapper.FileInfoMapper;
import study.jdnc7.homeworkproject.feature.file.model.entity.FileInfo;
import study.jdnc7.homeworkproject.feature.file.model.entity.UploadFile;
import study.jdnc7.homeworkproject.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileInfoMapper fileInfoMapper;

    @Transactional
    public List<FileInfo> insertFiles(Long userId, UploadFile uploadFile) throws IOException {
        List<MultipartFile> files = uploadFile.getFiles();
        List<FileInfo> fileInfos = new ArrayList<>();
        for (MultipartFile file : files) {
            String filePath = FileUtil.getFilePath(file);
            FileInfo fileInfo = new FileInfo(
                    file.getName(),
                    file.getOriginalFilename(),
                    filePath,
                    userId
            );
            fileInfoMapper.insert(fileInfo);
            fileInfos.add(fileInfo);
        }

        return fileInfos;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<File> getFile(Long fileId) {
        FileInfo info = fileInfoMapper.findById(fileId).orElseThrow(() -> new RuntimeException("해당 파일정보가 존재하지 않습니다."));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(info.getFileOriginName()).build());
        return new ResponseEntity<File>(new File(info.getFilePath()), headers, HttpStatus.OK);
    }
}
