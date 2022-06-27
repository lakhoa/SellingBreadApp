package com.example.SellingBreadApp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderItemDetailRequestDTO {
    private Long toppingId;
    @Max(value = 9, message = "less than 9")
    private Integer quantityTopping;

    public Long getToppingId() {
        return toppingId;
    }

    public void setToppingId(Long toppingId) {
        this.toppingId = toppingId;
    }

    public Integer getQuantityTopping() {
        return quantityTopping;
    }

    public void setQuantityTopping(Integer quantityTopping) {
        this.quantityTopping = quantityTopping;
    }
}
