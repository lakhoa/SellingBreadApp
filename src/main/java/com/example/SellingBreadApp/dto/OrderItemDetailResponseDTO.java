package com.example.SellingBreadApp.dto;

public class OrderItemDetailResponseDTO {
    private String toppingName;
    private Integer quantityTopping;
    private Double toppingPriceUnit;

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public Integer getQuantityTopping() {
        return quantityTopping;
    }

    public void setQuantityTopping(Integer quantityTopping) {
        this.quantityTopping = quantityTopping;
    }

    public Double getToppingPriceUnit() {
        return toppingPriceUnit;
    }

    public void setToppingPriceUnit(Double toppingPriceUnit) {
        this.toppingPriceUnit = toppingPriceUnit;
    }
}
