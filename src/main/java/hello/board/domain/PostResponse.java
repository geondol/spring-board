package hello.board.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private int viewCnt;
    private Boolean noticeYn;
    private Boolean deleteYn;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
}
