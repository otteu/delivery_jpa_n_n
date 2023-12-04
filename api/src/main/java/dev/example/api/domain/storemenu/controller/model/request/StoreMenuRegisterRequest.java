package dev.example.api.domain.storemenu.controller.model.request;

import dev.example.api.domain.store.controller.model.response.StoreResponse;
import dev.example.db.domain.store.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreMenuRegisterRequest {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String thumbnailUrl;
}
