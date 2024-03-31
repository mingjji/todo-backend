package backend.likelion.todos.auth;

import backend.likelion.todos.auth.jwt.JwtService;
import backend.likelion.todos.common.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtService jwtService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // TODO [6단계] parameter가 @Auth 어노테이션을 갖고 있고, 파라미터 타입이 Long.class인 경우 true를 반환하는 조건을 구현하세요.
        // -> 컨트롤러의 파라미터가, Resolver에서 지원하는 타입인지 체크 (어노테이션, 타입 등 체크)
        return parameter.hasParameterAnnotation(Auth.class) && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(
            //실제로 파라미터를 해석하는 로직 -> 여기서 반환된게 컨트롤러의 파라미터로 이용됨.(memberId)
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        // TODO [6단계] webRequest로부터 accessToken을 추출하고, jwtService를 사용하여 memberId를 추출하여 반환하는 로직을 구현하세요.
        String accessToken = extractAccessToken(webRequest);
        return jwtService.extractMemberId(accessToken);
    }

    private static String extractAccessToken(NativeWebRequest request) {
        // TODO [6단계] request 헤더에서 "Authorization" 헤더 값을 추출하여 "Bearer "로 시작하는 accessToken을 반환하세요. 유효하지 않을 경우 "로그인 후 접근할 수 있습니다." 메시지와 함께 UnAuthorizedException을 발생시키는 로직을 구현하세요.
        String accessToken = request.getHeader("Authorization");
        if (StringUtils.hasText(accessToken) && accessToken.startsWith("Bearer ")) {
            return accessToken.substring(7);
        }
        throw new UnAuthorizedException("로그인 후 접근할 수 있습니다.");
    }

}
