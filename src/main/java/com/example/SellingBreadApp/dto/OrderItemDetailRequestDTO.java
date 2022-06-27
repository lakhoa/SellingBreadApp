package com.example.SellingBreadApp.dto;

public class OrderItemDetailRequestDTO {
    private Long toppingId;
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
