package dev.example.api.common.converter;

import dev.example.api.common.annotation.Converter;
import dev.example.api.domain.user.model.User;
import dev.example.db.domain.user.UserEntity;

@Converter
public class AuthoticationConverter {
    public UserEntity toEntity(User user) {

        UserEntity userEntity = UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .status(user.getStatus())
                .address(user.getAddress())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .lastLoginAt(user.getLastLoginAt())
                .build();

        return userEntity;
    }

}
