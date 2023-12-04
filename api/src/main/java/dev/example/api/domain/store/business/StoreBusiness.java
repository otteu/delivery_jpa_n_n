package dev.example.api.domain.store.business;

import dev.example.api.common.annotation.Business;
import dev.example.api.domain.store.controller.model.request.StoreRegisterRequest;
import dev.example.api.domain.store.controller.model.response.StoreResponse;
import dev.example.api.domain.store.convertor.StoreConverter;
import dev.example.api.domain.store.service.StoreService;
import dev.example.db.domain.store.StoreEntity;
import dev.example.db.domain.store.enums.StoreCategory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class StoreBusiness {

    private final StoreService storeService;

    private final StoreConverter storeConverter;

    public StoreResponse register(
            StoreRegisterRequest storeRegisterRequest
    ) {

        StoreEntity entity = storeConverter.toEntity(storeRegisterRequest);
        StoreEntity newEntity = storeService.register(entity);
        StoreResponse response = storeConverter.toResponse(newEntity);
        return response;
    }

    public List<StoreResponse> searchCategory(
        StoreCategory storeCategory
    ) {
        List<StoreEntity> storeList = storeService.searchByCategory(storeCategory);

        return storeList.stream()
                .map(storeConverter::toResponse)
                .collect(Collectors.toList());
    }

}
