package hello.board.domain.file;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class FileForm {

    private Long id;
    private String filename;
    private List<MultipartFile> imageFiles;
}
