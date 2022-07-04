package com.example.SellingBreadApp.dto;

import java.util.List;
import javax.validation.constraints.Min;

public class OrderItemRequestDTO {

  private Long productId;
  @Min(value = 1, message = "must greater than 0")
  private Integer quantityItem;
  List<OrderItemDetailRequestDTO> itemRequestDTOList;

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Integer getQuantityItem() {
    return quantityItem;
  }

  public void setQuantityItem(Integer quantityItem) {
    this.quantityItem = quantityItem;
  }

  public List<OrderItemDetailRequestDTO> getItemRequestDTOList() {
    return itemRequestDTOList;
  }

  public void setItemRequestDTOList(List<OrderItemDetailRequestDTO> itemRequestDTOList) {
    this.itemRequestDTOList = itemRequestDTOList;
  }

  public OrderItemRequestDTO() {
  }
}
