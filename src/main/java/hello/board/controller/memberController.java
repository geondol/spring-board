package hello.board.controller;

import hello.board.domain.Member;
import hello.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class memberController {

    private final MemberService memberService;

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
