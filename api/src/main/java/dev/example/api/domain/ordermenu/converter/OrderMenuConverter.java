package dev.example.api.domain.ordermenu.converter;

import dev.example.api.common.annotation.Converter;
import dev.example.db.domain.order.OrderEntity;
import dev.example.db.domain.ordermenu.OrderMenuEntity;
import dev.example.db.domain.storemenu.StoreMenuEntity;

@Converter
public class OrderMenuConverter {

    public OrderMenuEntity toEntity(
            OrderEntity userOrderEntity,
            StoreMenuEntity storeMenuEntity
    ){
        return OrderMenuEntity.builder()
                .order(userOrderEntity)
                .storeMenu(storeMenuEntity)
                .build()
                ;
    }

}
