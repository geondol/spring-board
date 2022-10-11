package hello.board.domain;

import lombok.Data;

@Data
public class Member {
    private Long memberId;
    private String id;
    private String password;
    private String name;
    private String phone;
}
