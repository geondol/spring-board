package hello.board.controller;

import hello.board.domain.Board;
import hello.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/save")
    public String save(){
        return "/board/saveForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Board board, RedirectAttributes redirectAttributes){
        Board saveBoard = boardService.save(board);
        redirectAttributes.addAttribute("boardId", saveBoard.getBoardId());
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model){
        model.addAttribute("board",boardService.findById(boardId));
        return "/board/board";
    }

    @GetMapping("/hello")
    public String hello(){
        return "/board/hello";
    }

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("cnt", boardService.boardCount());
        model.addAttribute("test", boardService.boardList());
        return "/board/hello";
    }

    @GetMapping
    public String main(Model model){
        model.addAttribute("boards",boardService.boardList());
        return "/board/boards";
    }
}
