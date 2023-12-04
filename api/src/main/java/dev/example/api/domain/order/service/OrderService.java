package dev.example.api.domain.order.service;


import dev.example.api.common.error.ErrorCode;
import dev.example.api.common.exception.ApiException;
import dev.example.db.domain.order.OrderEntity;
import dev.example.db.domain.order.enums.OrderStatus;
import dev.example.db.domain.user.UserEntity;
import dev.example.db.repository.order.OrderRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.PanelUI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
//@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    // test





    public OrderEntity getOrderWithThrow(
            Long id,
            UserEntity userEntity
    ) {
        return orderRepository.findAllByIdAndStatusAndUser(id, OrderStatus.REGISTERED, userEntity)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<OrderEntity> getOrderList(
            UserEntity userEntity
    ) {
        return orderRepository.findAllByUserAndStatusOrderByIdDesc(userEntity, OrderStatus.REGISTERED);
    }

    public List<OrderEntity> getOrderList(
            UserEntity userEntity,
            List<OrderStatus> statusList
    ) {
        return orderRepository.findAllByUserAndStatusInOrderByIdDesc(userEntity, statusList);
    }

    // 현재 진행중인 내역
    public List<OrderEntity> current(UserEntity user) {
        return getOrderList(
                user,
                List.of(
                        OrderStatus.ORDER,
                        OrderStatus.COOKING,
                        OrderStatus.DELIVERY,
                        OrderStatus.ACCEPT
                )
        );
    }

    // 과거 주문 내역
    public List<OrderEntity> history(UserEntity user) {
        return getOrderList(
                user,
                List.of(
                        OrderStatus.RECEIVE
                )
        );
    }


    // 주문
    public OrderEntity order(
        OrderEntity orderEntity
    ) {
        return  Optional.ofNullable(orderEntity)
                .map(it -> {
                    it.setStatus(OrderStatus.ORDER);
                    it.setOrderedAt(LocalDateTime.now());
                    return orderRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 상태 변경
    public OrderEntity setStatus(OrderEntity orderEntity, OrderStatus status) {
        orderEntity.setStatus(status);
        return orderRepository.save(orderEntity);
    }

    // 조리 시작
    public OrderEntity accept(OrderEntity orderEntity) {
        orderEntity.setAcceptedAt(LocalDateTime.now());
        return setStatus(orderEntity, OrderStatus.ACCEPT);
    }

    // 배달 시작
    public OrderEntity delivery(OrderEntity orderEntity) {
        orderEntity.setDeliveryStartedAt(LocalDateTime.now());
        return setStatus(orderEntity, OrderStatus.DELIVERY);
    }

    // 배달 시작
    public OrderEntity receive(OrderEntity orderEntity) {
        orderEntity.setReceivedAt(LocalDateTime.now());
        return setStatus(orderEntity, OrderStatus.RECEIVE);
    }

    public List<OrderEntity> test() {
        List<OrderEntity> orderQueryDtos = orderRepository.findOrderQueryDtos();
        return orderQueryDtos;
    }

    public List<OrderEntity> test1() {
        List<OrderEntity> orderQueryDtos = orderRepository.findOrderQueryOrd();
        return orderQueryDtos;
    }

    public List<OrderEntity> test2() {
        List<OrderEntity> orderQueryDtos = orderRepository.findOrderQueryDtos();
        return orderQueryDtos;
    }
}
