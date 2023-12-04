package dev.example.api.domain.storemenu.controller.model.response;

import dev.example.db.domain.store.StoreEntity;
import dev.example.db.domain.storemenu.enums.StoreMenuStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.Store;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreMenuResponse {

    private Long id;

    private StoreEntity store;

    private String name;

    private BigDecimal amount;

    private StoreMenuStatus status;

    private String thumbnailUrl;

    private int likeCount;

    private int sequence;

}
