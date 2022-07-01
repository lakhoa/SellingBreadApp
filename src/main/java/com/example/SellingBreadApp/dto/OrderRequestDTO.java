package com.example.SellingBreadApp.dto;
import java.util.List;

public class OrderRequestDTO {
    private List<OrderItemRequestDTO> orderItemRequestDTOList;

    public List<OrderItemRequestDTO> getOrderItemRequestDTOList() {
        return orderItemRequestDTOList;
    }

    public void setOrderItemRequestDTOList(List<OrderItemRequestDTO> orderItemRequestDTOList) {
        this.orderItemRequestDTOList = orderItemRequestDTOList;
    }

    public OrderRequestDTO() {}
}
