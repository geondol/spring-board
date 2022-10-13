package hello.board.service;

import hello.board.domain.Board;
import hello.board.domain.Heart;
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

    @Transactional
    public void countUp(Long boardId){
        boardMapper.countUp(boardId);
    }

    public int findHeart(Long memberId, Long boardId){
        return boardMapper.findHeart(memberId,boardId);
    }

    @Transactional
    public int insertHeart(Heart heart){
        // 좋아요가 DB에 저장이 되는것이 없으면 0이 그대로 리턴으로 넘어감
        int result = 0;
        // 좋아요가 이미 있는지 확인하는 코드
        int find = boardMapper.findHeart(heart.getMemberId(), heart.getBoardId());

        // find가 null이면 좋아요가 없는 상태이므로 정보 저장
        // find가 null이 아니면 좋아요가 있는 상태이므로 정보 삭제
        if(find==0) {
            // insert의 리턴값은 DB에 성공적으로 insert된 갯수를 보내므로 result가 1이 됨
            result = boardMapper.insertHeart(heart);
        } else {
            boardMapper.deleteHeart(heart);
        }
        // 0 or 1이 담겨져서 @Controller에 보냄.
        return result;
    }

    public void deleteHeart(Heart heart){
        boardMapper.deleteHeart(heart);
    }
}
