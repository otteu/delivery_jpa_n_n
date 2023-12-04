package dev.example.api.domain.user.converter;

import dev.example.api.common.annotation.Converter;
import dev.example.api.common.error.ErrorCode;
import dev.example.api.common.exception.ApiException;
import dev.example.api.domain.user.controller.model.request.UserRegisterRequest;
import dev.example.api.domain.user.controller.model.response.UserResponse;
import dev.example.db.domain.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request) {

        return Optional.ofNullable(request)
                .map(it -> {
                    return UserEntity.builder()
                            .name(request.getName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .address(request.getAddress())
                            .build();
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
    }

    public UserResponse toResponse(UserEntity userEntity) {

        return Optional.ofNullable(userEntity)
                .map(it -> {
                    return UserResponse.builder()
                            .Id(userEntity.getId())
                            .name(userEntity.getName())
                            .email(userEntity.getEmail())
                            .address(userEntity.getAddress())
                            .status(userEntity.getStatus())
                            .createdBy(userEntity.getCreatedBy())
                            .createdDate(userEntity.getCreatedDate())
                            .lastModifiedBy(userEntity.getLastModifiedBy())
                            .lastModifiedDate(userEntity.getLastModifiedDate())
                            .registeredAt(userEntity.getRegisteredAt())
                            .lastLoginAt(userEntity.getLastLoginAt())
                            .unregisteredAt(userEntity.getUnregisteredAt())
                            .build();
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "userEntity Null"));

    }
}
