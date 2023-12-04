package dev.example.api.domain.storemenu.controller;


import dev.example.api.common.api.Api;
import dev.example.api.domain.storemenu.business.StoreMenuBusiness;
import dev.example.api.domain.storemenu.controller.model.response.StoreMenuResponse;
import dev.example.db.domain.store.StoreEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store-menu")
public class StoreMenuApiController {
    private final StoreMenuBusiness storeMenuBusiness;

    @GetMapping("/search")
    public Api<List<StoreMenuResponse>> search(
            @RequestParam Long storeId
            ){
        var response = storeMenuBusiness.search(storeId);
        return Api.OK(response);
    }


}
