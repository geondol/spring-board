package hello.board.domain.file;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class FileStore {

    @Value("${file}")
    private String fileDir;
    //이미지가 저장될 경로

    public String getFullPath(String filename){
        return fileDir + filename;
    }

    //여러개 파일 저장
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {

        //들어온 파일을 담을 리스트 생성
        List<UploadFile> storeFileResult = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles){
            if (!multipartFile.isEmpty()){
                //반복해서 파일을 하나씩 서버로 보냄
                UploadFile uploadFile = storeFile(multipartFile);
                //List에 추가
                storeFileResult.add(uploadFile);
            }
        }
        return storeFileResult;
    }

    //파일 하나 저장
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()){
            return null;
        }
        log.info("들어온 파일 = {}" ,multipartFile);
        String originFilename = multipartFile.getOriginalFilename();
        log.info("getOrigin = {}",originFilename);

        //서버에 저장하는 파일명
        String DBFileName = createDBFileName(originFilename);

        //지정한 경로로 서버에 저장할 파일명을 보냄
        multipartFile.transferTo(new File(getFullPath(DBFileName)));

        return new UploadFile(originFilename,DBFileName);
    }

    //데이터 베이스에 저장할 파일 이름 생성 메소드
    public String createDBFileName(String originFilename){

        //마지막 . 뒤에오는 확장자명 뽑기 ex=(png)
        int pos = originFilename.lastIndexOf(".");
        String ext = originFilename.substring(pos + 1);

        //서버에 저장하는 파일명
        String uuid = UUID.randomUUID().toString();

        String DBFileName = uuid + "." + ext;
        return DBFileName;
    }


}
