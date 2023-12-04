package dev.example.api.resolver;


import dev.example.api.common.annotation.UserSession;
import dev.example.api.domain.user.model.User;
import dev.example.api.domain.user.service.UserService;
import dev.example.db.domain.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver {


    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원 파라미터 체크, 어노테이션 체크
        // 어노테이션 채크
        boolean annotation = parameter.hasParameterAnnotation(UserSession.class);
        // 파아미터 타입 체크
        boolean parameterType = parameter.getParameterType().equals(User.class);


        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 위 통과 하면 여기 옴

        // reques holder 찾기
        RequestAttributes requestContext = RequestContextHolder.getRequestAttributes();
        Object userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

        UserEntity userEntity = userService.getUserWithThrow(Long.parseLong(userId.toString()));

        // user info

        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .status(userEntity.getStatus())
                .password(userEntity.getPassword())
                .address(userEntity.getAddress())
                .registeredAt(userEntity.getRegisteredAt())
                .unregisteredAt(userEntity.getUnregisteredAt())
                .lastLoginAt(userEntity.getLastLoginAt())
                .build();
    }
}
