package dev.example.api.domain.user.controller;


import dev.example.api.common.annotation.UserSession;
import dev.example.api.common.api.Api;
import dev.example.api.domain.user.business.UserBusiness;
import dev.example.api.domain.user.controller.model.response.UserResponse;
import dev.example.api.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/re")
    public Api<UserResponse> me(
            @UserSession User user
    )
    {

        UserResponse response = userBusiness.me(user);
        return Api.OK(response);
    }

}
