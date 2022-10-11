package hello.board.mapper;

import hello.board.domain.Board;
import hello.board.domain.Heart;
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

    Heart findHeart(Long memberId, Long boardId);

}
