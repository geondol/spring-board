package hello.board.domain;

import lombok.Data;

@Data
public class Heart {
    private Long heartId;
    private Long boardId;
    private Long memberId;
    private int heart;
}
