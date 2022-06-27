package com.example.SellingBreadApp.service;

import com.example.SellingBreadApp.dto.HistoryOrderResponseDTO;
import com.example.SellingBreadApp.dto.OrderRequestDTO;
import com.example.SellingBreadApp.dto.OrderResponseDTO;
import com.example.SellingBreadApp.dto.ResponseDTO;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrdersService {
    ResponseDTO<OrderResponseDTO> createOrder(OrderRequestDTO orderRequestDTO);
    ResponseDTO<List<HistoryOrderResponseDTO>> getOrder(Pageable pageable);
    ResponseDTO<OrderResponseDTO> getOrderDetail(Long orderId);
    ResponseDTO<List<HistoryOrderResponseDTO>> getOrderByDate(Date date,Pageable pageable);
    ResponseDTO<List<HistoryOrderResponseDTO>> getOrderByDateBetween(Date dateStart, Date dateEnd,Pageable pageable);
}
