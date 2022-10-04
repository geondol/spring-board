package hello.board.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {

    private Long boardId;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private int read;
    private String name;

}
