package hello.board.mapper;

import hello.board.domain.Board;
import hello.board.domain.Heart;
import hello.board.domain.file.File;
import hello.board.domain.file.UploadFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    void save(Board board);

    void deleteById(Long id);

    void update(Board board);

    Board findById(Long id);

    int boardCount(String title, String name);

    List<Board> findAll(String title,String name);

    void countUp(Long boardId);

    int findHeart(Long memberId, Long boardId);

    int insertHeart(Heart heart);

    void deleteHeart(Heart heart);

    void filesave(File file);

}
