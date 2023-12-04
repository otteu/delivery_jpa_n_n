package dev.example.api.domain.store.controller;

import dev.example.api.common.api.Api;
import dev.example.api.domain.store.business.StoreBusiness;
import dev.example.api.domain.store.controller.model.response.StoreResponse;
import dev.example.db.domain.store.enums.StoreCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
public class StoreApiController {

    private final StoreBusiness storeBusiness;

    @GetMapping("/search")
    public Api<List<StoreResponse>> search(
            @RequestParam(required = false)
            StoreCategory storeCategory
    ) {
        List<StoreResponse> response = storeBusiness.searchCategory(storeCategory);
        return Api.OK(response);
    }




}
