package dev.example.db.repository.order.enums;


import lombok.AllArgsConstructor;


public enum OrderStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ORDER("주문"),
    ACCEPT("확인"),
    COOKING("요리중"),
    DELIVERY("배달중"),
    RECEIVE("완료")
            ;

    OrderStatus(String description){
        this.description = description;
    }

    private String description;
}
