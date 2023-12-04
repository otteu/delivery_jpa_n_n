package dev.example.db.repository.order;

import dev.example.db.domain.order.OrderEntity;
import dev.example.db.domain.order.enums.OrderStatus;
import dev.example.db.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> , OrderRepositoryCustom{

    List<OrderEntity> findAllByUserAndStatusOrderByIdDesc(UserEntity user, OrderStatus status);

    List<OrderEntity> findAllByUserAndStatusInOrderByIdDesc(UserEntity user, List<OrderStatus> status);

    Optional<OrderEntity> findAllByIdAndStatusAndUser(Long id, OrderStatus status, UserEntity user);




}
