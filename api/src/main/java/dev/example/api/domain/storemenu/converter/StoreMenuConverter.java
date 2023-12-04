package dev.example.api.domain.storemenu.converter;

import dev.example.api.common.annotation.Converter;
import dev.example.api.common.error.ErrorCode;
import dev.example.api.common.exception.ApiException;
import dev.example.api.domain.storemenu.controller.model.request.StoreMenuRegisterRequest;
import dev.example.api.domain.storemenu.controller.model.response.StoreMenuResponse;
import dev.example.db.domain.store.StoreEntity;
import dev.example.db.domain.storemenu.StoreMenuEntity;

import java.util.Optional;

@Converter
public class StoreMenuConverter {
    public StoreMenuEntity toEntity(
            StoreMenuRegisterRequest request,
            StoreEntity storeEntity
    ){

        return Optional.ofNullable(request)
                .map(it ->{

                    return StoreMenuEntity.builder()
                            .store(storeEntity)
                            .name(request.getName())
                            .amount(request.getAmount())
                            .thumbnailUrl(request.getThumbnailUrl())
                            .build()
                            ;

                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }


    public StoreMenuResponse toResponse(
            StoreMenuEntity storeMenuEntity
    ){
        return Optional.ofNullable(storeMenuEntity)
                .map(it ->{
                    return StoreMenuResponse.builder()
                            .id(storeMenuEntity.getId())
                            .name(storeMenuEntity.getName())
                            .store(storeMenuEntity.getStore())
                            .amount(storeMenuEntity.getAmount())
                            .status(storeMenuEntity.getStatus())
                            .thumbnailUrl(storeMenuEntity.getThumbnailUrl())
                            .likeCount(storeMenuEntity.getLikeCount())
                            .sequence(storeMenuEntity.getSequence())
                            .build()
                            ;
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }
}
