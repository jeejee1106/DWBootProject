package com.jeejee.webservice.web;

//import com.jeejee.webservice.domain.PostsRepository;
//import com.jeejee.webservice.dto.PostsMainResponseDto;
//import com.jeejee.webservice.dto.posts.PostsSaveRequestDto;
//import com.jeejee.webservice.service.PostsService;
//import lombok.AllArgsConstructor;
import com.jeejee.webservice.web.dto.WebRestControllerDto;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import java.util.List;

///**
// * @RestController는 @ResponseBody를 모든 메서드에 적용해준다.
// * 즉 hello 메서드는 "HelloWorld" 라는 문자열 데이터를 JSON 형태로 반환하게 된다.
// */
//
@RestController //JSON을 반환하는 컨트롤러
//@AllArgsConstructor //모든 필드를 인자값으로 하는 생성자를 생성
public class WebRestController {
    //
//    private PostsRepository postsRepository;
//    private PostsService postsService;
//
//    /**
//     * 눈에 보이지는 않지만 롬복의 @AllArgsConstructor로 인해 현재 모든 필드 값을 파라미터로 받는 생성자가 만들어졌다.
//     * 생성자가 하나일 땐 @Autowired의 생략이 가능하다.
//     * 즉, 현재 생성자에 의존성 주입이 되어있는 상태이다!!
//     */
//
    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @GetMapping("/hello/dto")
    public WebRestControllerDto restDro(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new WebRestControllerDto(name, amount);
    }
//
//    @PostMapping("/posts")
//    public List<PostsMainResponseDto> savePost(@RequestBody PostsSaveRequestDto dto) {
//        return postsService.findAllDesc();
//    }
}
