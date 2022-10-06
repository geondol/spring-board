create table table_board(
    boardId bigint auto_increment,
    title varchar(30) not null ,
    content varchar(1000) not null ,
    name varchar(30) not null ,
    view_cnt int default 0,
    primary key (boardId)
);

INSERT INTO table_board(title, content, name) VALUES('title1', 'content1', 'name1');
INSERT INTO table_board(title, content, name) VALUES('title2', 'content2', 'name2');
INSERT INTO table_board(title, content, name) VALUES('title3', 'content3', 'name3');
INSERT INTO table_board(title, content, name) VALUES('title4', 'content4', 'name4');
INSERT INTO table_board(title, content, name) VALUES('title5', 'content5', 'name5');
INSERT INTO table_board(title, content, name) VALUES('title6', 'content6', 'name6');
INSERT INTO table_board(title, content, name) VALUES('title7', 'content7', 'name7');
INSERT INTO table_board(title, content, name) VALUES('title8', 'content8', 'name8');

drop table table_board;
select * from table_board;
desc table_board;
show variables like 'lower_case_table_names';

select count(boardId)
from table_board
where name like concat('%w%');