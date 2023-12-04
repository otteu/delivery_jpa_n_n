package dev.example.api.domain.storemenu.controller;

import dev.example.api.common.api.Api;
import dev.example.api.domain.storemenu.business.StoreMenuBusiness;
import dev.example.api.domain.storemenu.controller.model.request.StoreMenuRegisterRequest;
import dev.example.api.domain.storemenu.controller.model.response.StoreMenuResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/store-menu")
public class StoreMenuOpenApiController {

    private final StoreMenuBusiness storeMenuBusiness;

    @PostMapping("/{storeId}/register")
    public Api<StoreMenuResponse> register(
            @Valid
            @RequestBody Api<StoreMenuRegisterRequest> request,
            @PathVariable Long storeId
    ){
        var req = request.getBody();
        var response = storeMenuBusiness.register(req, storeId);
        return Api.OK(response);
    }

}
