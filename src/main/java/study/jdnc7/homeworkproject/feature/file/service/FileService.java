package study.jdnc7.homeworkproject.feature.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import study.jdnc7.homeworkproject.domain.file.mapper.FileInfoMapper;
import study.jdnc7.homeworkproject.domain.file.model.FileInfo;
import study.jdnc7.homeworkproject.feature.board.model.BoardRequest;
import study.jdnc7.homeworkproject.util.SecurityUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileInfoMapper fileInfoMapper;
    private final String filePath = "/save/";

    @Transactional
    public List<FileInfo> insertFiles(Long userId, BoardRequest boardRequest) throws IOException {
        List<MultipartFile> files = boardRequest.getFiles();
        List<FileInfo> fileInfos = new ArrayList<>();
        for (MultipartFile file : files) {
            String itemFilePath = filePath + createFileName(file.getOriginalFilename());
            file.transferTo(new File(itemFilePath));

            FileInfo fileInfo = new FileInfo(
                    file.getName(),
                    file.getOriginalFilename(),
                    itemFilePath,
                    userId
            );
            fileInfoMapper.insert(fileInfo);
            fileInfos.add(fileInfo);
        }

        return fileInfos;
    }

    public String createFileName (String originalFileName) {
        return UUID.fromString(originalFileName).toString();
    }
}
