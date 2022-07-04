package com.example.SellingBreadApp.dto;

import java.util.List;
import javax.validation.constraints.Min;

public class OrderResponseDTO {

  private List<OrderItemResponseDTO> orderItemResponseDTOList;
  @Min(value = 1, message = "must greater than 0")
  private Double totalPrice;

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public List<OrderItemResponseDTO> getOrderItemResponseDTOList() {
    return orderItemResponseDTOList;
  }

  public void setOrderItemResponseDTOList(List<OrderItemResponseDTO> orderItemResponseDTOList) {
    this.orderItemResponseDTOList = orderItemResponseDTOList;
  }
}
