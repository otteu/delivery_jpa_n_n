package dev.example.api.domain.order.controller;


import dev.example.api.common.annotation.UserSession;
import dev.example.api.common.api.Api;
import dev.example.api.domain.order.business.OrderBusiness;
import dev.example.api.domain.order.controller.model.request.OrderRequest;
import dev.example.api.domain.order.controller.model.response.OrderDetailResponse;
import dev.example.api.domain.order.controller.model.response.OrderResponse;
import dev.example.api.domain.order.service.OrderService;
import dev.example.api.domain.user.model.User;
import dev.example.db.domain.order.OrderEntity;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-order")
public class OrderController {

    private final OrderBusiness orderBusiness;

    private final OrderService orderService;


    @PostMapping("")
    public Api<OrderResponse> userOrder(
            // orderMenuList 로 받음
            @Valid
            @RequestBody Api<OrderRequest> userOrderRequest,
            @Parameter(hidden = true)
            @UserSession User user
    ) {

        OrderResponse orderResponse = orderBusiness.userOrder(userOrderRequest.getBody(), user);
        return Api.OK(orderResponse);
    }


    // 현재 진행 중인 주문건
    @GetMapping("/current")
    public Api<List<OrderEntity>> current(
            @Parameter(hidden = true)
            @UserSession
            User user
    ) {
        List<OrderEntity> orderEntities = orderBusiness.current(user);
        return Api.OK(orderEntities);
    }


    // 과거 주문건
//    @GetMapping("/history")
//    public Api<List<OrderDetailResponse>> history(
//            @Parameter(hidden = true)
//            @UserSession
//            User user
//    ) {
//        orderBusiness.history(user);
//    }
//
//
//    // 주문 1건에 대한 내역
//    @GetMapping("/id/{orderId}")
//    public Api<OrderDetailResponse> read(
//            @Parameter(hidden = true)
//            @UserSession
//            User user,
//            @PathVariable Long orderId
//    ) {
//        orderBusiness.read(user, orderId);
//
//    }














    @GetMapping("/open-api/test")
    private List<OrderEntity> test() {
        List<OrderEntity> test = orderService.test();



        return test;
    }
    @GetMapping("/open-api/test1")
    private List<OrderEntity> test1() {
        List<OrderEntity> test = orderService.test1();



        return test;
    }
//
//    @GetMapping("/open-api/test1")
//    private List<OrderEntity> test1() {
//        List<OrderEntity> test = orderService.test1();
//        return test;
//    }
//
//    @GetMapping("/open-api/test2")
//    private List<OrderEntity> test2() {
//        List<OrderEntity> test = orderService.test2();
//        return test;
//    }

}
