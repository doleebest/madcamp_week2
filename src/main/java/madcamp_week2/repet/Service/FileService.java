package madcamp_week2.repet.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file.dir}")
    private String fileDir;

    public String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        String originalFileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid + extension;
        String savePath = fileDir + savedFileName;

        file.transferTo(new File(savePath));

        return savedFileName;
    }

    public void deleteFile(String fileName) {
        if (fileName == null) {
            return;
        }

        File file = new File(fileDir + fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}