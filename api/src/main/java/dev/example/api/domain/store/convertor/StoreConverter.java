package dev.example.api.domain.store.convertor;

import dev.example.api.common.annotation.Converter;
import dev.example.api.common.error.ErrorCode;
import dev.example.api.common.exception.ApiException;
import dev.example.api.domain.store.controller.model.request.StoreRegisterRequest;
import dev.example.api.domain.store.controller.model.response.StoreResponse;
import dev.example.db.domain.store.StoreEntity;

import java.util.Optional;

@Converter
public class StoreConverter {

    public StoreEntity toEntity (
            StoreRegisterRequest request
    ) {
        // NULL check
        return Optional.ofNullable(request)
                .map(it ->{
                    return StoreEntity.builder()
                            .name(request.getName())
                            .address(request.getAddress())
                            .category(request.getStoreCategory())
                            .minimumAmount(request.getMinimumAmount())
                            .minimumDeliveryAmount(request.getMinimumDeliveryAmount())
                            .thumbnailUrl(request.getThumbnailUrl())
                            .phoneNumber(request.getPhoneNumber())
                            .build()
                            ;
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreResponse toResponse(
            StoreEntity entity
    ) {
        return Optional.ofNullable(entity)
                .map(it ->{
                    return StoreResponse.builder()
                            .id(entity.getId())
                            .name(entity.getName())
                            .status(entity.getStatus())
                            .category(entity.getCategory())
                            .address(entity.getAddress())
                            .minimumAmount(entity.getMinimumAmount())
                            .minimumDeliveryAmount(entity.getMinimumDeliveryAmount())
                            .thumbnailUrl(entity.getThumbnailUrl())
                            .phoneNumber(entity.getPhoneNumber())
                            .star(entity.getStar())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

}
