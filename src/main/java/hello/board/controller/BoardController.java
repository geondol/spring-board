package hello.board.controller;

import hello.board.domain.Board;
import hello.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/save")
    public String save(){
        return "/board/saveForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Board board, RedirectAttributes redirectAttributes){
        boardService.save(board);
        redirectAttributes.addAttribute("boardId", board.getBoardId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model){
        model.addAttribute("board",boardService.findById(boardId));
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
    public String main(@RequestParam(required = false) String title,String name, Model model){
        model.addAttribute("boards",boardService.boardList(title,name));
        int count = boardService.boardCount(title,name);
        model.addAttribute("count",count);
        return "/board/boards";
    }
}
