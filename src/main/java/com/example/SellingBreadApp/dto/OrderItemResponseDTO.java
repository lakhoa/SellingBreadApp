package com.example.SellingBreadApp.dto;

import java.util.List;

public class OrderItemResponseDTO {
    private String productName;
    private Double productPriceUnit;
    private Integer quantityItem;

    private List<OrderItemDetailResponseDTO> orderItemDetailResponseDTOList;

    public List<OrderItemDetailResponseDTO> getOrderItemDetailResponseDTOList() {
        return orderItemDetailResponseDTOList;
    }

    public void setOrderItemDetailResponseDTOList(List<OrderItemDetailResponseDTO> orderItemDetailResponseDTOList) {
        this.orderItemDetailResponseDTOList = orderItemDetailResponseDTOList;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPriceUnit() {
        return productPriceUnit;
    }

    public void setProductPriceUnit(Double productPriceUnit) {
        this.productPriceUnit = productPriceUnit;
    }

    public Integer getQuantityItem() {
        return quantityItem;
    }

    public void setQuantityItem(Integer quantityItem) {
        this.quantityItem = quantityItem;
    }
}
