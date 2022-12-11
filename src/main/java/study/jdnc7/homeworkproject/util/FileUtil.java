package study.jdnc7.homeworkproject.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUtil {

    private final static String savePath = "/save/";

    public static String getFilePath (MultipartFile multipartFile) throws IOException {
        String filePath = createFilePath(multipartFile.getOriginalFilename());
        saveFile(multipartFile, filePath);
        return filePath;
    }


    private static void saveFile (MultipartFile file, String filePath) throws IOException {
        file.transferTo(new File(filePath));
    }

    private static String createFilePath (String originFileName) {
        return savePath + createFileName(originFileName);
    }

    private static String createFileName (String originalFileName) {
        return UUID.fromString(originalFileName).toString();
    }
}
