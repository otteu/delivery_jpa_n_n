package dev.example.api.domain.order.business;

import dev.example.api.common.annotation.Business;
import dev.example.api.common.converter.AuthoticationConverter;
import dev.example.api.domain.order.controller.model.request.OrderRequest;
import dev.example.api.domain.order.controller.model.response.OrderDetailResponse;
import dev.example.api.domain.order.controller.model.response.OrderResponse;
import dev.example.api.domain.order.converter.OrderConverter;
import dev.example.api.domain.order.service.OrderService;
import dev.example.api.domain.ordermenu.converter.OrderMenuConverter;
import dev.example.api.domain.ordermenu.service.OrderMenuService;
import dev.example.api.domain.store.service.StoreService;
import dev.example.api.domain.storemenu.service.StoreMenuService;
import dev.example.api.domain.user.model.User;
import dev.example.db.domain.order.OrderEntity;
import dev.example.db.domain.ordermenu.OrderMenuEntity;
import dev.example.db.domain.store.StoreEntity;
import dev.example.db.domain.storemenu.StoreMenuEntity;
import dev.example.db.domain.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;

@RequiredArgsConstructor
@Business
public class OrderBusiness {

    private final OrderService orderService;
    private final StoreMenuService storeMenuService;

    private final OrderConverter orderConverter;

    private final OrderMenuService orderMenuService;

    private final OrderMenuConverter orderMenuConverter;

    private final AuthoticationConverter authoticationConverter;
    private final StoreService storeService;


    // 사용자, 메뉴 Id
    public OrderResponse userOrder(
            OrderRequest body,
            User user
    ) {
        // 변환 User
        UserEntity userEntity = authoticationConverter.toEntity(user);

        // Menu 있는 지 검증
        List<StoreMenuEntity> storeMenuEntityList = body.getStoreMenuIdList().stream()
                .map(it -> storeMenuService.findOne(it))
                .collect(Collectors.toList());

        StoreEntity orderStore = storeService.getStoreOneWithThrow(body.getStoreId());

        // 변환 및 값 할당
        OrderEntity userOrderEntity = orderConverter.toEntity(userEntity, storeMenuEntityList, orderStore);

        // 주문
        OrderEntity newUserOrderEntity = orderService.order(userOrderEntity);

        // 메뉴 값 할등 하기 위해
        // 매핑
        List<OrderMenuEntity> userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(it -> {
                    // menu +user order
                    OrderMenuEntity userOrderMenuEntity = orderMenuConverter.toEntity(newUserOrderEntity, it);
                    return userOrderMenuEntity;
                })
                .collect(Collectors.toList());

        // 저장
        // 주문내역 기록 남기기
        userOrderMenuEntityList.forEach(it ->{
            orderMenuService.order(it);
        });


        // response
        return orderConverter.toResponse(newUserOrderEntity);

    }

    public List<OrderEntity> current(User user) {

        UserEntity userEntity = authoticationConverter.toEntity(user);
        List<OrderEntity> orderEntities = orderService.current(userEntity);

        return orderEntities;
    }

    public void history(User user) {
    }

    public void read(User user, Long orderId) {
    }
}
