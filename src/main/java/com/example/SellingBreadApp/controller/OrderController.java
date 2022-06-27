package com.example.SellingBreadApp.controller;

import com.example.SellingBreadApp.dto.HistoryOrderResponseDTO;
import com.example.SellingBreadApp.dto.OrderRequestDTO;
import com.example.SellingBreadApp.dto.OrderResponseDTO;
import com.example.SellingBreadApp.dto.ResponseDTO;
import com.example.SellingBreadApp.service.OrdersService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    OrdersService ordersService;

    @PostMapping("/order")
    public ResponseEntity<ResponseDTO<OrderResponseDTO>> create(@RequestBody OrderRequestDTO orders){
        ResponseDTO <OrderResponseDTO>  rs =  ordersService.createOrder(orders);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    @GetMapping("/orderList")
    public ResponseEntity<ResponseDTO<List<HistoryOrderResponseDTO>>> get(Pageable pageable){
        ResponseDTO <List<HistoryOrderResponseDTO>>  rs =  ordersService.getOrder(pageable);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<ResponseDTO<OrderResponseDTO>> getDetail(@PathVariable Long id){
        ResponseDTO <OrderResponseDTO>  rs =  ordersService.getOrderDetail(id);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    @GetMapping("/orderListByDate")
    public ResponseEntity<ResponseDTO<List<HistoryOrderResponseDTO>>> getByTime(@RequestParam("at") @DateTimeFormat(pattern="yyyy-MM-dd") Date date, Pageable pageable) {
        ResponseDTO <List<HistoryOrderResponseDTO>>  rs =  ordersService.getOrderByDate(date, pageable);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    @GetMapping("/orderListByDateBetween")
    public ResponseEntity<ResponseDTO<List<HistoryOrderResponseDTO>>> getByTimeBetween(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateStart, @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEnd, Pageable pageable) {
        ResponseDTO <List<HistoryOrderResponseDTO>>  rs =  ordersService.getOrderByDateBetween(dateStart,dateEnd, pageable);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}

