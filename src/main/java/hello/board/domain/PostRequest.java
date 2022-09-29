package hello.board.domain;

import lombok.Data;

@Data
public class PostRequest {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private Boolean noticeYn;
}
