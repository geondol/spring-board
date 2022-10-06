package hello.board.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {

    private Long boardId;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private int viewCnt;
    //카멜 표기법을 써서 view_cnt 이렇게하면 인식이 안된다
    private String name;

}
