package dev.example.api.domain.user.controller;

import dev.example.api.common.api.Api;
import dev.example.api.domain.token.controller.model.TokenResponse;
import dev.example.api.domain.user.business.UserBusiness;
import dev.example.api.domain.user.controller.model.request.UserLoginRequest;
import dev.example.api.domain.user.controller.model.request.UserRegisterRequest;
import dev.example.api.domain.user.controller.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
public class UserOpenApiController {

    private final UserBusiness userBusiness;


    // 사용자 가입 요청
    @PostMapping("/register")
    public Api<UserResponse> register (
        @Valid
        @RequestBody Api<UserRegisterRequest> request
    ) {
        UserResponse response = userBusiness.register(request.getBody());
        System.out.println("sdsdsds");
        return Api.OK(response);
    }

    @PostMapping("/login")
    public Api<TokenResponse> login (
            @Valid
            @RequestBody Api<UserLoginRequest> request
    ) {

        TokenResponse response = userBusiness.login(request.getBody());
        return Api.OK(response);

    }

}
