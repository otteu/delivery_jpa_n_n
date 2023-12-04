package dev.example.db.repository.ordermenu;

import dev.example.db.domain.order.OrderEntity;
import dev.example.db.domain.order.enums.OrderStatus;
import dev.example.db.domain.ordermenu.OrderMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMenuRepository extends JpaRepository<OrderMenuEntity, Long> {

    List<OrderMenuEntity> findAllByOrderAndStatus(OrderEntity order, OrderStatus status);

}
