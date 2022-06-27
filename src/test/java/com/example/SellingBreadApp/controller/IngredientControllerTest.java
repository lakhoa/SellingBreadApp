package com.example.SellingBreadApp.controller;
import com.example.SellingBreadApp.repository.ToppingRepository;
import com.example.SellingBreadApp.service.ToppingService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
class IngredientControllerTest {
  private ToppingService toppingService;
  private ToppingRepository toppingRepository;

  private MockMvc mockMvc;


}