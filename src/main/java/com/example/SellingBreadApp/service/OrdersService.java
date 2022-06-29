package com.example.SellingBreadApp.service;
import com.example.SellingBreadApp.dto.HistoryOrderResponseDTO;
import com.example.SellingBreadApp.dto.OrderRequestDTO;
import com.example.SellingBreadApp.dto.OrderResponseDTO;
import com.example.SellingBreadApp.dto.ResponseDTO;
import com.example.SellingBreadApp.exception.CannotAddToppingToProductException;
import com.example.SellingBreadApp.exception.CustomException;
import com.example.SellingBreadApp.exception.InvalidSumToppingQuantityException;
import com.example.SellingBreadApp.exception.NotFoundOrderException;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrdersService {
    ResponseDTO<OrderResponseDTO> createOrder(OrderRequestDTO orderRequestDTO)
        throws CustomException, CannotAddToppingToProductException, InvalidSumToppingQuantityException;
    ResponseDTO<List<HistoryOrderResponseDTO>> getOrder(Pageable pageable);
    ResponseDTO<OrderResponseDTO> getOrderDetail(Long orderId) throws NotFoundOrderException;
    ResponseDTO<List<HistoryOrderResponseDTO>> getOrderByDate(Date date,Pageable pageable);
    ResponseDTO<List<HistoryOrderResponseDTO>> getOrderByDateBetween(Date dateStart, Date dateEnd,Pageable pageable);
}
