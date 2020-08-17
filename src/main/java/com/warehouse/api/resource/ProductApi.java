package com.warehouse.api.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductApi {

    public ProductApi(){}

    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal unitPrice;
    private String description;
    private String category;
    private Long unitsInStock;
    private Long unitsInOrder;
    private boolean soldOut;
    private ProductState state = ProductState.BOUGHT;
}
