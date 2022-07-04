package com.example.SellingBreadApp.mapper;

import com.example.SellingBreadApp.dto.*;
import com.example.SellingBreadApp.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

  public OrderResponseDTO convertToOrderResponseDTO(Orders orders) {
    OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
    List<OrderItem> orderItemsList = orders.getOrderItems();
    List<OrderItemResponseDTO> orderItemResponseDTOList = new ArrayList<>();
    for (OrderItem orderItem : orderItemsList) {
      OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
      List<OrderItemDetail> orderItemDetailList = orderItem.getOrderItemDetails();
      List<OrderItemDetailResponseDTO> orderItemDetailResponseDTOList = new ArrayList<>();
      for (OrderItemDetail orderItemDetail : orderItemDetailList) {
        OrderItemDetailResponseDTO orderItemDetailResponseDTO = new OrderItemDetailResponseDTO();
        orderItemDetailResponseDTO.setToppingName(orderItemDetail.getToppingName());
        orderItemDetailResponseDTO.setQuantityTopping(orderItemDetail.getQuantityTopping());
        orderItemDetailResponseDTO.setToppingPriceUnit(orderItemDetail.getToppingPriceUnit());
        orderItemDetailResponseDTOList.add(orderItemDetailResponseDTO);
      }
      orderItemResponseDTO.setOrderItemDetailResponseDTOList(orderItemDetailResponseDTOList);
      orderItemResponseDTO.setQuantityItem(orderItem.getQuantity());
      orderItemResponseDTO.setProductName(orderItem.getProductName());
      orderItemResponseDTO.setProductPriceUnit(orderItem.getProductPriceUnit());
      orderItemResponseDTOList.add(orderItemResponseDTO);
    }
    orderResponseDTO.setOrderItemResponseDTOList(orderItemResponseDTOList);
    orderResponseDTO.setTotalPrice(orders.getTotalPrice());
    return orderResponseDTO;
  }

  public HistoryOrderResponseDTO convertToHistoryOrderResponseDTO(Orders orders) {
    HistoryOrderResponseDTO historyOrderResponseDTO = new HistoryOrderResponseDTO();
    historyOrderResponseDTO.setId(orders.getId());
    historyOrderResponseDTO.setCreatedDate(orders.getCreateDate());
    historyOrderResponseDTO.setTotalPrice(orders.getTotalPrice());
    return historyOrderResponseDTO;
  }
}
