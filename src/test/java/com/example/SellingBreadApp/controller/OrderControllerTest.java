package com.example.SellingBreadApp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.SellingBreadApp.dto.OrderItemDetailRequestDTO;
import com.example.SellingBreadApp.dto.OrderItemRequestDTO;
import com.example.SellingBreadApp.dto.OrderRequestDTO;
import com.example.SellingBreadApp.entity.Product;
import com.example.SellingBreadApp.entity.Topping;
import com.example.SellingBreadApp.exception.ExceptionControllerAdvice;
import com.example.SellingBreadApp.repository.ProductRepository;
import com.example.SellingBreadApp.repository.ToppingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Transactional
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class OrderControllerTest {

  private MockMvc mockMvc;


  @Autowired
  private ExceptionControllerAdvice exceptionControllerAdvice;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver;

  @Autowired
  private ToppingRepository toppingRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderController orderController;

  private Product product;

  private Topping topping;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(orderController)
        .setCustomArgumentResolvers(pageableHandlerMethodArgumentResolver)
        .setControllerAdvice(exceptionControllerAdvice)
        .build();
    initDb();
  }

  void initDb() {
    topping = new Topping();
    topping.setName("Topping1");
    topping.setPrice(1000.0);
    toppingRepository.save(topping);
    List<Topping> toppings = new ArrayList<>();
    toppings.add(topping);
    product = new Product();
    product.setName("Product1");
    product.setPrice(1000.0d);
    product.setMaxTopping(2);
    product.setToppings(toppings);
    productRepository.save(product);
  }

  private OrderRequestDTO initDtoOrders() {
    OrderItemDetailRequestDTO orderItemDetailRequestDTO = new OrderItemDetailRequestDTO();
    orderItemDetailRequestDTO.setQuantityTopping(1);
    orderItemDetailRequestDTO.setToppingId(topping.getId());
    List<OrderItemDetailRequestDTO> orderItemDetailRequestDTOS = new ArrayList<>();
    orderItemDetailRequestDTOS.add(orderItemDetailRequestDTO);

    OrderItemRequestDTO orderItemRequestDTO = new OrderItemRequestDTO();
    orderItemRequestDTO.setItemRequestDTOList(orderItemDetailRequestDTOS);
    orderItemRequestDTO.setQuantityItem(1);
    orderItemRequestDTO.setProductId(product.getId());

    List<OrderItemRequestDTO> orderItemRequestDTOS = new ArrayList<>();
    orderItemRequestDTOS.add(orderItemRequestDTO);

    OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
    orderRequestDTO.setOrderItemRequestDTOList(orderItemRequestDTOS);
    return orderRequestDTO;
  }

  public String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void shouldCreateOrdersWithoutError() throws Exception {
    mockMvc.perform(post("/api/v1/order")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(initDtoOrders())))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("The order is added"))
        .andExpect(jsonPath("$.data.totalPrice").value(2000))
        .andExpect(jsonPath("$.data.orderItemResponseDTOList[0].productName").value("Product1"))
        .andExpect(jsonPath("$.data.orderItemResponseDTOList[0].productPriceUnit").value(1000))
        .andExpect(jsonPath("$.data.orderItemResponseDTOList[0].quantityItem").value(1))
        .andExpect(jsonPath(
            "$.data.orderItemResponseDTOList[0].orderItemDetailResponseDTOList[0].toppingName").value(
            "Topping1"))
        .andExpect(jsonPath(
            "$.data.orderItemResponseDTOList[0].orderItemDetailResponseDTOList[0].quantityTopping").value(
            1))
        .andExpect(jsonPath(
            "$.data.orderItemResponseDTOList[0].orderItemDetailResponseDTOList[0].toppingPriceUnit").value(
            1000));
  }

  @Test
  void shouldCreateOrdersWithErrorIfWrongConditionOfField() throws Exception {
    OrderItemDetailRequestDTO orderItemDetailRequestDTO = new OrderItemDetailRequestDTO();
    orderItemDetailRequestDTO.setQuantityTopping(10);
    orderItemDetailRequestDTO.setToppingId(topping.getId());
    List<OrderItemDetailRequestDTO> orderItemDetailRequestDTOS = new ArrayList<>();
    orderItemDetailRequestDTOS.add(orderItemDetailRequestDTO);

    OrderItemRequestDTO orderItemRequestDTO = new OrderItemRequestDTO();
    orderItemRequestDTO.setItemRequestDTOList(orderItemDetailRequestDTOS);
    orderItemRequestDTO.setQuantityItem(1);
    orderItemRequestDTO.setProductId(product.getId());

    List<OrderItemRequestDTO> orderItemRequestDTOS = new ArrayList<>();
    orderItemRequestDTOS.add(orderItemRequestDTO);
    OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
    orderRequestDTO.setOrderItemRequestDTOList(orderItemRequestDTOS);
    mockMvc.perform(post("/api/v1/order")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(orderRequestDTO)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void getOrdersList() throws Exception {
    mockMvc.perform(get("/api/v1/orderList")
            .content("{\"page\": 0,\"size\": 5,\"sort\": [\"totalPrice\"]}"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data").isArray());
  }

  @Test
  void getOrdersWithIdWithoutError() throws Exception {
    mockMvc.perform(post("/api/v1/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(initDtoOrders()))
    );
    mockMvc.perform(get("/api/v1/order/{id}", 20))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.totalPrice").value(2000));
  }

  @Test
  void getOrdersByTimeWithoutErrors() throws Exception {
    mockMvc.perform(post("/api/v1/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(initDtoOrders()))
    );
    LocalDateTime convertDateToString = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
    String dateTime = convertDateToString.format(formatter);
    mockMvc.perform(get("/api/v1/orderListByDate")
            .param("at", dateTime)
            .content("{\"page\": 0,\"size\": 5,\"sort\": [\"totalPrice\"]}"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void getOrdersByDateTimeByStartEndDayWithoutError() throws Exception {
    mockMvc.perform(post("/api/v1/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(initDtoOrders()))
    ).andDo(print());
    LocalDate convertDateToString = LocalDate.now();
    String startTime = "2022-06-27";
    String endTime = convertDateToString.toString();
    mockMvc.perform(get("/api/v1/orderListByDateBetween")
            .param("from", startTime)
            .param("to", endTime)
            .content("{\"page\": 0,\"size\": 5,\"sort\": [\"totalPrice\"]}"))
        .andDo(print())
        .andExpect(status().isOk());
  }
}

