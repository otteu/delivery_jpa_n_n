package dev.example.db.domain.order;

import dev.example.db.domain.BaseEntity;
import dev.example.db.domain.store.StoreEntity;
import dev.example.db.domain.user.UserEntity;
import dev.example.db.domain.order.enums.OrderStatus;
import dev.example.db.domain.ordermenu.OrderMenuEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
@Setter
@Table(name = "orders")
public class OrderEntity extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity store;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderMenuEntity> orderMenuList = new ArrayList<>();



    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private OrderStatus status;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    private LocalDateTime orderedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime cookingStartedAt;

    private LocalDateTime deliveryStartedAt;

    private LocalDateTime receivedAt;

}
