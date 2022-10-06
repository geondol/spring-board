package hello.board.service;

import hello.board.domain.Board;
import hello.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
//과거에는 일반적으로 @Autowired 또는 @Inject 어노테이션을 이용하여 빈(Bean)을 주입받고는 했었습니다.
//스프링은 생성자로 빈(Bean)을 주입하는 방식을 권장한다고 하는데요.
//해당 어노테이션은 롬복(Lombok)에서 제공해주는 기능으로,
//클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 만들어주는 역할을 합니다.
@Transactional(readOnly = true)
public class BoardService {

    private final BoardMapper boardMapper;

    @Transactional
    public void save(Board board){
        boardMapper.save(board);
    }

    public Board findById(Long id){
        return boardMapper.findById(id);
    }

    @Transactional
    public void update(Board board){
        boardMapper.update(board);
    }

    @Transactional
    public void deleteById(Long id){
        boardMapper.deleteById(id);
    }

    public int boardCount(String title, String name){
        return boardMapper.boardCount(title,name);
    }

    public List<Board> boardList(String title,String name){
        return boardMapper.findAll(title,name);
    }


}
