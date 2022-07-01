package com.example.SellingBreadApp.controller;
import com.example.SellingBreadApp.dto.HistoryOrderResponseDTO;
import com.example.SellingBreadApp.dto.OrderRequestDTO;
import com.example.SellingBreadApp.dto.OrderResponseDTO;
import com.example.SellingBreadApp.dto.PageResponseDTO;
import com.example.SellingBreadApp.dto.ResponseDTO;
import com.example.SellingBreadApp.exception.CannotAddToppingToProductException;
import com.example.SellingBreadApp.exception.CustomException;
import com.example.SellingBreadApp.exception.InvalidSumToppingQuantityException;
import com.example.SellingBreadApp.exception.NotFoundOrderException;
import com.example.SellingBreadApp.service.OrdersService;
import java.util.Date;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class OrderController {

    private final OrdersService ordersService;
    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }



    @PostMapping("/order")
    public ResponseEntity<ResponseDTO<OrderResponseDTO>> create(@RequestBody OrderRequestDTO orders)
        throws CannotAddToppingToProductException, CustomException, InvalidSumToppingQuantityException {
        ResponseDTO <OrderResponseDTO>  rs =  ordersService.createOrder(orders);
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/orderList")
    public ResponseEntity<PageResponseDTO<List<HistoryOrderResponseDTO>>> get(Pageable pageable){
        PageResponseDTO <List<HistoryOrderResponseDTO>>  rs =  ordersService.getOrder(pageable);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<ResponseDTO<OrderResponseDTO>> getDetail(@PathVariable Long id)
        throws NotFoundOrderException {
        ResponseDTO <OrderResponseDTO>  rs =  ordersService.getOrderDetail(id);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    @GetMapping("/orderListByDate")
    public ResponseEntity<PageResponseDTO<List<HistoryOrderResponseDTO>>> getByTime(@RequestParam("at") @DateTimeFormat(pattern="yyyy-MM-dd") Date date, Pageable pageable) {
        PageResponseDTO <List<HistoryOrderResponseDTO>>  rs =  ordersService.getOrderByDate(date, pageable);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    @GetMapping("/orderListByDateBetween")
    public ResponseEntity<PageResponseDTO<List<HistoryOrderResponseDTO>>> getByTimeBetween(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateStart, @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEnd, Pageable pageable) {
        PageResponseDTO <List<HistoryOrderResponseDTO>>  rs =  ordersService.getOrderByDateBetween(dateStart,dateEnd, pageable);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}

