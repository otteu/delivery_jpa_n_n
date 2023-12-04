package dev.example.db.repository.order;

import dev.example.db.domain.order.OrderEntity;
import dev.example.db.domain.ordermenu.OrderMenuEntity;
import dev.example.db.domain.user.UserEntity;

import java.util.List;
import java.util.Map;

public interface OrderRepositoryCustom {


    List<OrderEntity> findAllWithUser();

    Map<Long, List<OrderMenuEntity>> findOrderMenus(List<Long> orderIds);


    List<OrderEntity> findOrderQueryDtos();


    List<OrderEntity> findOrderQueryOrd();

}
