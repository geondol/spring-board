create table table_board(
    id bigint(20) not null AUTO_INCREMENT comment 'PK',
    title varchar(100) not null comment '제목',
    content varchar(3000) not null comment '내용',
    writer varchar(20) not null comment '작성자',
    view_cnt int(11) not null comment '조회 수',
    notice_yn tinyint(1) not null comment '공지글 여부',
    delete_yn tinyint(1) not null comment '삭제 여부',
    created_date datetime not null default current_timestamp() comment '생성일시',
    modified_date datetime default null comment '최종 수정일시',
    primary key (id)
) comment '게시글';

select * from table_board;
desc table_board;