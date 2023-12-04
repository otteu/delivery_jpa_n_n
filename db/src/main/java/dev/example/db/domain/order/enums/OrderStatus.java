package dev.example.db.domain.order.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ORDER("주문"),
    ACCEPT("확인"),
    COOKING("요리중"),
    DELIVERY("배달중"),
    RECEIVE("완료"),
    ;

//    UserOrderStatus(String description){
//        this.description = description;
//    }
    private String description;

}
