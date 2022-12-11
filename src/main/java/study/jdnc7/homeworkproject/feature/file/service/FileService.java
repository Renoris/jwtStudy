package study.jdnc7.homeworkproject.feature.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import study.jdnc7.homeworkproject.feature.file.mapper.FileInfoMapper;
import study.jdnc7.homeworkproject.feature.file.model.entity.FileInfo;
import study.jdnc7.homeworkproject.feature.file.model.entity.UploadFile;
import study.jdnc7.homeworkproject.util.FileUtil;

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
}
