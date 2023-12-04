package dev.example.api.domain.storemenu.business;

import dev.example.api.common.annotation.Business;
import dev.example.api.domain.store.controller.model.response.StoreResponse;
import dev.example.api.domain.store.service.StoreService;
import dev.example.api.domain.storemenu.controller.model.request.StoreMenuRegisterRequest;
import dev.example.api.domain.storemenu.controller.model.response.StoreMenuResponse;
import dev.example.api.domain.storemenu.converter.StoreMenuConverter;
import dev.example.api.domain.storemenu.service.StoreMenuService;
import dev.example.db.domain.store.StoreEntity;
import dev.example.db.domain.store.enums.StoreStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    private final StoreService storeService;

    public StoreMenuResponse register(
            StoreMenuRegisterRequest request,
            Long storeId
    ){
        StoreEntity storeEntity = storeService.getStoreOneWithThrow(storeId);

        // req -> entity -> save -> response
        var entity = storeMenuConverter.toEntity(request, storeEntity);
        var newEntity = storeMenuService.register(entity);
        var response = storeMenuConverter.toResponse(newEntity);
        return response;
    }

    public List<StoreMenuResponse> search(
            Long storeId
    ){
        StoreEntity storeEntity = storeService.getStoreWithThrow(storeId);
        var list = storeMenuService.getStoreMenuByStoreId(storeEntity);

        return list.stream()
                .map(it ->{
                    return storeMenuConverter.toResponse(it);
                })
                //.map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());
    }


}
