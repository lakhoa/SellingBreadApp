package com.example.SellingBreadApp.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.example.SellingBreadApp.dto.HistoryOrderResponseDTO;
import com.example.SellingBreadApp.dto.ResponseDTO;
import com.example.SellingBreadApp.entity.Orders;
import com.example.SellingBreadApp.entity.Product;
import com.example.SellingBreadApp.exception.InvalidSumToppingQuantityException;
import com.example.SellingBreadApp.repository.OrdersRepository;
import com.example.SellingBreadApp.service.implement.OrdersServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// TODO: What is this????
//@ExtendWith(MockitoExtension.class)
public class OrdersServiceTest {
  @Mock
  OrdersRepository ordersRepository;

  @Autowired
  private OrdersService ordersService;

  private MockMvc mockMvc;


  @Test
  void whenGetAll_shouldReturnList() {

    // 1. create mock data
    Product product = new Product();
    product.setId(1L);
    product.setMaxTopping(8);
    product.setPrice(30.0);
    product.setName("Banh mi");


    // 2. define behavior of Repository
//    @Test
//    public void shouldReturnAddressWhenFindById() {
//      when(ordersService.getOrder(anyLong()))
//          .thenReturn(Optional.of(createAddress()));

      // 3. call service method

      // 4. assert the result

      // 4.1 ensure repository is called

    }
  }

