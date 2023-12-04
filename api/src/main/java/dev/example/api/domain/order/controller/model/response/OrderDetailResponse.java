package dev.example.api.domain.order.controller.model.response;


import dev.example.api.domain.store.controller.model.response.StoreResponse;
import dev.example.api.domain.storemenu.controller.model.response.StoreMenuResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailResponse {

    private List<OrderResponse> orderResponse;
    private List<StoreResponse> storeResponse;
//    private List<StoreMenuResponse> storeMenuResponseList;



}
