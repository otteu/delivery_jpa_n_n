package dev.example.db.domain.ordermenu.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderMenuStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ;

//    UserOrderMenuStatus(String description){
//        this.description = description;
//    }
    private String description;

}
