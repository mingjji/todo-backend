package backend.likelion.todos.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//컨트롤러는 데이터나 뷰 반환함.
//RestContreller는 데이터를 반환함.
@RequestMapping("/test")
public class AuthTestController {

    @GetMapping("/auth")
    public String test(
            @Auth Long memberId
    ) {
        return "아이디가 " + memberId + "인 회원 인증 성공!";
    }
}
