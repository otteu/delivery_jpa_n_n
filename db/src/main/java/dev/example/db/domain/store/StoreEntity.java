package dev.example.db.domain.store;


import dev.example.db.domain.BaseEntity;
import dev.example.db.domain.store.enums.StoreCategory;
import dev.example.db.domain.store.enums.StoreStatus;
import dev.example.db.domain.storemenu.StoreMenuEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
@Setter
@Table(name = "store")
public class StoreEntity extends BaseEntity {

    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String address;


    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<StoreMenuEntity> menus = new ArrayList<>();

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    private double star;

    @Column(length = 200, nullable = false)
    private String thumbnailUrl;

    //Precision : 전체 자릿수 개수  Scale: 소수점 오른쪽부터 자릿수 개수
    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal minimumAmount;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal minimumDeliveryAmount;

    @Column(length = 20)
    private String phoneNumber;



}