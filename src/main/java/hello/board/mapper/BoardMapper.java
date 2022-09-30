package hello.board.mapper;

import hello.board.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    Board save(Board board);

    void deleteById(Long id);

    void update();

    Board findById(Long id);

    int boardCount();

    List<Board> findAll();
}
