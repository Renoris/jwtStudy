package study.jdnc7.homeworkproject.feature.file.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.jdnc7.homeworkproject.feature.file.service.FileService;

import java.io.File;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/{fileId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<File> getFile (@PathVariable Long fileId) {
        return fileService.getFile(fileId);
    }
}
