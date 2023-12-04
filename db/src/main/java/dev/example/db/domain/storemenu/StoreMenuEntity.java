package dev.example.db.domain.storemenu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.example.db.domain.BaseEntity;
import dev.example.db.domain.ordermenu.OrderMenuEntity;
import dev.example.db.domain.store.StoreEntity;
import dev.example.db.domain.storemenu.enums.StoreMenuStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Setter
@Getter
@Table(name = "store_menu")
public class StoreMenuEntity extends BaseEntity {

    @Id
    @Column(name = "store_menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity store;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "storeMenu")
//    private List<OrderMenuEntity> orderMenuList = new ArrayList<>();

    @Column(length = 100, nullable = false)
    private String name;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreMenuStatus status;

    @Column(length = 200, nullable = false)
    private String thumbnailUrl;

    private int likeCount;

    private int sequence;

    public void setStore(StoreEntity storeEntity) {
        this.store = storeEntity;
        storeEntity.getMenus().add(this);
    }

}
