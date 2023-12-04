package dev.example.api.domain.store.controller.model.response;

import dev.example.db.domain.store.enums.StoreCategory;
import dev.example.db.domain.store.enums.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponse {
    private Long id;

    private String name;

    private String address;

    private StoreStatus status;

    private StoreCategory category;

    private double star;

    private String thumbnailUrl;

    private BigDecimal minimumAmount;

    private BigDecimal minimumDeliveryAmount;

    private String phoneNumber;
}
