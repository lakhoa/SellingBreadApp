package com.example.SellingBreadApp.service;

import com.example.SellingBreadApp.entity.Product;
import com.example.SellingBreadApp.repository.OrdersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;


public class OrdersServiceTest {

  @Mock
  OrdersRepository ordersRepository;

  @Autowired
  private OrdersService ordersService;

  private MockMvc mockMvc;


  @Test
  void whenGetAll_shouldReturnList() {
    Product product = new Product();
    product.setId(1L);
    product.setMaxTopping(8);
    product.setPrice(30.0);
    product.setName("Banh mi");
  }
}

