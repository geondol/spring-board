package hello.board.controller;

import hello.board.domain.Member;
import hello.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class memberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login(){
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Member member, HttpServletRequest request,RedirectAttributes redirectAttributes){
        Member findMember = memberService.login(member);
        String password = member.getPassword();

        if (password.equals(findMember.getPassword())) {
            //로그인한 아이디의 memberId를 가져온다
            Long memberId = findMember.getMemberId();
            //memberId를 /boards로 보냄
            redirectAttributes.addAttribute("memberId",memberId);

            HttpSession session = request.getSession();
            session.setAttribute("loginMember",findMember.getId());
            return "redirect:/boards";
        }else {
            return "member/passwordError";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session != null){
            session.invalidate();//세션 삭제
        }
        return "redirect:/members/login";
    }

    @GetMapping("/save")
    public String signUp(){
        return "/member/signUp";
    }

    @PostMapping("/save")
    public String signUp(@ModelAttribute Member member, RedirectAttributes redirectAttributes){
        memberService.save(member);
        redirectAttributes.addAttribute("memberId",member.getMemberId());
        return "redirect:/members/{memberId}";
    }

    @GetMapping("/{memberId}")
    public String detail(@PathVariable Long memberId, Model model){
        Member findMember = memberService.findById(memberId);
        model.addAttribute("member",findMember);
        return "/member/member";
    }

    @GetMapping
    public String all(Model model){
        List<Member> allMember = memberService.findAll();
        model.addAttribute("members",allMember);
        return "/member/members";
    }
}
