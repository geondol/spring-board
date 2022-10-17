package hello.board.domain.file;

import lombok.Data;

import java.util.List;

@Data
public class File {

    private Long id;
    private String filename;
    private List<UploadFile> imageFiles;
}
