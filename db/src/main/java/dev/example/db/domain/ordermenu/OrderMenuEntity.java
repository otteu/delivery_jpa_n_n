package dev.example.db.domain.ordermenu;


import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.example.db.domain.BaseEntity;
import dev.example.db.domain.storemenu.StoreMenuEntity;
import dev.example.db.domain.order.OrderEntity;
import dev.example.db.domain.ordermenu.enums.OrderMenuStatus;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
@Setter
@Table(name = "order_menu")
public class OrderMenuEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_menu_id")
    private Long id;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_menu_id")
    private StoreMenuEntity storeMenu;



    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private OrderMenuStatus status;

}
