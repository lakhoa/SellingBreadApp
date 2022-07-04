package com.example.SellingBreadApp.dto;

import javax.validation.constraints.Min;


public class OrderItemDetailRequestDTO {

  private Long toppingId;
  @Min(value = 1, message = "must greater than 0")
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

  public OrderItemDetailRequestDTO(Long toppingId, Integer quantityTopping) {
    this.toppingId = toppingId;
    this.quantityTopping = quantityTopping;
  }

  public OrderItemDetailRequestDTO() {
  }
}
