package dev.example.api.domain.order.controller.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    // 주문

    @NotNull
    private List<Long> storeMenuIdList;

    @NotNull
    private Long storeId;

}
