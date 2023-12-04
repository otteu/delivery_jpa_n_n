package dev.example.api.domain.user.business;

import dev.example.api.common.annotation.Business;
import dev.example.api.domain.token.business.TokenBusiness;
import dev.example.api.domain.token.controller.model.TokenResponse;
import dev.example.api.domain.user.controller.model.request.UserLoginRequest;
import dev.example.api.domain.user.controller.model.request.UserRegisterRequest;
import dev.example.api.domain.user.controller.model.response.UserResponse;
import dev.example.api.domain.user.converter.UserConverter;
import dev.example.api.domain.user.model.User;
import dev.example.api.domain.user.service.UserService;
import dev.example.db.domain.user.UserEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    public UserResponse register(UserRegisterRequest request) {

        UserEntity entity = userConverter.toEntity(request);
        UserEntity newEntity = userService.register(entity);
        UserResponse response = userConverter.toResponse(newEntity);
        return response;

//        return Optional.ofNullable(request)
//                .map(userConverter::toEntity)
//                .map(userService::register)
//                .map(userConverter::toResponse)
//                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "request null"));

    }

    // 1. email, password
    // 2. user entity 로그인 확인
    // 3. token 생성
    // 4. token response

    public TokenResponse login(UserLoginRequest request) {
        UserEntity userEntity = userService.login(request.getEmail(), request.getPassword());
        TokenResponse tokenResponse = tokenBusiness.issueToken(userEntity);
        return tokenResponse;
    }

    public UserResponse me(User user) {
        UserEntity userEntity = userService.getUserWithThrow(user.getId());
        UserResponse response = userConverter.toResponse(userEntity);
        return response;
    }
}
