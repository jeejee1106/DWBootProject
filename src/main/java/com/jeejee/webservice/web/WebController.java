//package com.jeejee.webservice.web;
//
//import com.jeejee.webservice.config.auth.dto.SessionUser;
//import com.jeejee.webservice.service.PostsService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//@AllArgsConstructor
//public class WebController {
//
//    private PostsService postsService;
//    private HttpSession httpSession;
//
//    @GetMapping("/")
//    public String main(Model model) {
//        model.addAttribute("posts", postsService.findAllDesc());
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//        if (user != null) {
//            model.addAttribute("userName", user.getName());
//        }
//        return "main"; //머스테치 스타터로 인해 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 확장자는 자동으로 지정된다.
//    }
//
//    @GetMapping("/posts/save")
//    public String postsSave() {
//        return "posts-save";
//    }
//}
