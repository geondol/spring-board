DROP TABLE IF EXISTS board;
CREATE TABLE board(
                      boardId int auto_increment,
                      title varchar (30) not null,
                      content varchar (30) not null,
                      name varchar (30) not null,
                      readd int default 0,
                      primary key(boardId)
);

INSERT INTO board(title, content, name) VALUES('title1', 'content1', 'name1');
INSERT INTO board(title, content, name) VALUES('title2', 'content2', 'name2');
INSERT INTO board(title, content, name) VALUES('title3', 'content3', 'name3');
INSERT INTO board(title, content, name) VALUES('title4', 'content4', 'name4');
INSERT INTO board(title, content, name) VALUES('title5', 'content5', 'name5');
INSERT INTO board(title, content, name) VALUES('title6', 'content6', 'name6');
INSERT INTO board(title, content, name) VALUES('title7', 'content7', 'name7');
INSERT INTO board(title, content, name) VALUES('title8', 'content8', 'name8');