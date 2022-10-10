package hello.board.controller;

import hello.board.service.KaKaoService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@AllArgsConstructor
public class KaKaoController {

    private KaKaoService kaKaoService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/member/kakao")
    public String kakaoCallback(@RequestParam("code") String code, HttpSession session, Model model){
        String access_Token = kaKaoService.getKakaoAccessToken(code);
        HashMap<String,Object> userInfo = kaKaoService.getUserInfo(access_Token);
        System.out.println("login Controller = " + userInfo);
        model.addAttribute("email",userInfo.get("email"));

        //클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null){
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token",access_Token);
        }

        return "index";
    }
}
