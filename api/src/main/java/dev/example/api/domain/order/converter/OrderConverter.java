package dev.example.api.domain.order.converter;

import dev.example.api.common.annotation.Converter;
import dev.example.api.domain.order.controller.model.response.OrderResponse;
import dev.example.api.domain.user.model.User;
import dev.example.db.domain.order.OrderEntity;
import dev.example.db.domain.store.StoreEntity;
import dev.example.db.domain.storemenu.StoreMenuEntity;
import dev.example.db.domain.user.UserEntity;

import java.math.BigDecimal;
import java.util.List;

@Converter
public class OrderConverter {


    public OrderEntity toEntity (
            UserEntity user,
            List<StoreMenuEntity> storeMenuEntityList,
            StoreEntity storeEntity
    ) {
        // 가격
        BigDecimal totalAmount = storeMenuEntityList.stream()
                .map(it -> it.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        return OrderEntity.builder()
                .user(user)
                .store(storeEntity)
                .amount(totalAmount)
                .build()
                ;
    }

    public OrderResponse toResponse(
            OrderEntity userOrderEntity
    ){
        return OrderResponse.builder()
                .id(userOrderEntity.getId())
                .status(userOrderEntity.getStatus())
                .amount(userOrderEntity.getAmount())
                .orderedAt(userOrderEntity.getOrderedAt())
                .acceptedAt(userOrderEntity.getAcceptedAt())
                .cookingStartedAt(userOrderEntity.getCookingStartedAt())
                .deliveryStartedAt(userOrderEntity.getDeliveryStartedAt())
                .receivedAt(userOrderEntity.getReceivedAt())
                .build()
                ;
    }



}
