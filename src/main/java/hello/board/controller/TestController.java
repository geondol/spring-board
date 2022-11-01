package hello.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//json 형식으로 보냄
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "hi";
    }

    @GetMapping("/hobbys")
    public List<String> hobbys(){
        List<String> hobbys = Arrays.asList("축구","수영","음악감상");
        return hobbys;
    }

    @GetMapping("/study")
    public Map<String,Object> study(){
        Map<String,Object> subject = new HashMap<>();
        subject.put("자바","Java");
        subject.put("JSP","Java Server Pages");
        subject.put("Spring","Spring Framework5");
        return subject;
    }
}
