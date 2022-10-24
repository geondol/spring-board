package hello.board.controller;

import hello.board.domain.Board;
import hello.board.domain.Heart;
import hello.board.domain.Member;
import hello.board.domain.file.File;
import hello.board.domain.file.FileStore;
import hello.board.domain.file.UploadFile;
import hello.board.service.BoardService;
import hello.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final FileStore fileStore;

    @GetMapping("/save")
    public String save(){
        return "/board/saveForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Board board, @ModelAttribute File form, RedirectAttributes redirectAttributes) throws IOException {

        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());
        log.info("컨트롤러에서 들어온 파일 이름 = {}",storeImageFiles);

        boardService.filesave(storeImageFiles);

        boardService.save(board);
        redirectAttributes.addAttribute("boardId", board.getBoardId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, @RequestParam("memberId") Long memberId , Model model,HttpServletRequest request ){
        boardService.countUp(boardId);
        model.addAttribute("board",boardService.findById(boardId));

        //세션으로 저장된 아이디 가져오기
        HttpSession session = request.getSession();
        Object loginMember = session.getAttribute("loginMember");
        log.info("로그인한 아이디 = {}",loginMember);

        log.info("memberId ={}",memberId);

        int heart = boardService.findHeart(memberId, boardId);
        model.addAttribute("heart",heart);
        model.addAttribute("memberId",memberId);
        log.info("heart = {}",heart);

        return "/board/board";
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId,Model model){
        Board findBoard = boardService.findById(boardId);
        model.addAttribute("board", findBoard);
        return "board/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String editForm(@ModelAttribute Board board,@PathVariable Long boardId){
        Board findBoard = boardService.findById(boardId);
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        findBoard.setName(board.getName());
        boardService.update(findBoard);
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/{boardId}/delete")
    public String delete(@PathVariable Long boardId){
        boardService.deleteById(boardId);
        return "redirect:/boards";
    }

    @GetMapping
    public String main(@RequestParam(required = false) String title,@RequestParam(value = "memberId",required = false) Long memberId, String name, Model model, HttpServletRequest request){
        model.addAttribute("boards",boardService.boardList(title,name));
        int count = boardService.boardCount(title,name);
        model.addAttribute("count",count);
        HttpSession session = request.getSession();
        Object loginMember = session.getAttribute("loginMember");
        model.addAttribute("login",loginMember);
        model.addAttribute("memberId",memberId);
        return "/board/boards";
    }

    //좋아요 버튼 클릭하면 실행되는 코드
    @PostMapping("/heart")
    public @ResponseBody int heart(@ModelAttribute Heart heart){
        int result = boardService.insertHeart(heart);
        return result;
    }

    @GetMapping("/test")
    public String test(Model model){
        String a = "안녕 나는 임건형이야";
        model.addAttribute("test", a);
        return "board/test";
    }

}
