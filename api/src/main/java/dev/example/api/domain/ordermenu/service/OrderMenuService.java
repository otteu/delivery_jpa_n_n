package dev.example.api.domain.ordermenu.service;

import dev.example.api.common.error.ErrorCode;
import dev.example.api.common.exception.ApiException;
import dev.example.db.domain.order.OrderEntity;
import dev.example.db.domain.order.enums.OrderStatus;
import dev.example.db.domain.ordermenu.OrderMenuEntity;
import dev.example.db.domain.ordermenu.enums.OrderMenuStatus;
import dev.example.db.repository.order.OrderRepository;
import dev.example.db.repository.ordermenu.OrderMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderMenuService {

    private final OrderMenuRepository orderMenuRepository;
    private final OrderRepository orderRepository;




//    public List<OrderMenuEntity> getOrderMenus(Long);

    public List<OrderMenuEntity> getOrderMenu(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
        return orderMenuRepository.findAllByOrderAndStatus(orderEntity, OrderStatus.REGISTERED);
    }

    public OrderMenuEntity order(
            OrderMenuEntity orderMenuEntity
    ) {
        return Optional.ofNullable(orderMenuEntity)
                .map(it -> {
                    it.setStatus(OrderMenuStatus.REGISTERED);
                    return orderMenuRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
