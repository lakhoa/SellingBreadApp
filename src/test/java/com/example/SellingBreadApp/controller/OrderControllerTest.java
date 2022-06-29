package com.example.SellingBreadApp.controller;
import com.example.SellingBreadApp.dto.HistoryOrderResponseDTO;
import com.example.SellingBreadApp.dto.OrderItemDetailRequestDTO;
import com.example.SellingBreadApp.dto.OrderItemRequestDTO;
import com.example.SellingBreadApp.dto.OrderRequestDTO;
import com.example.SellingBreadApp.entity.Product;
import com.example.SellingBreadApp.entity.Topping;
import com.example.SellingBreadApp.exception.ExceptionControllerAdvice;
import com.example.SellingBreadApp.repository.ProductRepository;
import com.example.SellingBreadApp.repository.ToppingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
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
  void setUp(){
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(orderController)
        .setCustomArgumentResolvers(pageableHandlerMethodArgumentResolver)
        .setControllerAdvice(exceptionControllerAdvice)
        .build();
    initDb();
  }

  void initDb(){
    topping = new Topping();
    topping.setName("Topping1");
    topping.setPrice(1000.0);
    toppingRepository.save(topping);
    List<Topping> toppings = new ArrayList<>();
    toppings.add(topping);
    product = new Product();
    product.setName("BBB");
    product.setPrice(1000.0d);
    product.setMaxTopping(2);
    product.setToppings(toppings);
    productRepository.save(product);
  }

  private OrderRequestDTO initDtoOrders(){
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

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void should_create_orders_without_error() throws Exception{
    mockMvc.perform(post("/order")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(initDtoOrders())))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("The order is added"))
        .andExpect(jsonPath("$.data.totalPrice").value(2000))
        .andExpect(jsonPath("$.data.orderItemResponseDTOList[0].productName").value("BBB"))
        .andExpect(jsonPath("$.data.orderItemResponseDTOList[0].productPriceUnit").value(1000))
        .andExpect(jsonPath("$.data.orderItemResponseDTOList[0].quantityItem").value(1))
        .andExpect(jsonPath("$.data.orderItemResponseDTOList[0].orderItemDetailResponseDTOList[0].toppingName").value("Topping1"))
        .andExpect(jsonPath("$.data.orderItemResponseDTOList[0].orderItemDetailResponseDTOList[0].toppingPriceUnit").value(1000));
  }
  @Test
  void should_create_orders_with_error_if_wrong_condition_of_field() throws Exception{
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
    mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(orderRequestDTO)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void get_orders_list() throws Exception{
    mockMvc.perform(get("/orderList")
        .content("{\n"
            + "  \"page\": 0,\n"
            + "  \"size\": 5,\n"
            + "  \"sort\": [\n"
            + "    \"totalPrice\"\n"
            + "  ]\n"
            + "}"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("The orders get all"))
        .andExpect(jsonPath("$.data").isArray());
  }

  @Test
  void get_orders_list_with_id_without_error() throws Exception {
    mockMvc.perform(post("/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(initDtoOrders()))
    );
    mockMvc.perform(get("/order/{id}" , 1))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("The order detail is get"))
        .andExpect(jsonPath("$.data.totalPrice").value(2000));
  }

  @Test
  void get_orders_by_time_without_errors() throws Exception{
    mockMvc.perform(post("/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(initDtoOrders()))
    );
    String dateTime = "2022-06-29";
    mockMvc.perform(get("/orderListByDate")
            .param("at",dateTime)
            .content("{\n"
                + "  \"page\": 0,\n"
                + "  \"size\": 5,\n"
                + "  \"sort\": [\n"
                + "    \"totalPrice\"\n"
                + "  ]\n"
                + "}"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("The orders get all"))
        .andExpect(jsonPath("$.data[0].totalPrice").value(2000));
  }

  @Test
  void get_orders_by_date_time_by_start_end_day_without_error() throws Exception{
    mockMvc.perform(post("/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(initDtoOrders()))
    ).andDo(print());
    String startTime = "2022-06-28";
    String endTime = "2022-06-29";
    mockMvc.perform(get("/orderListByDateBetween")
            .param("from",startTime)
            .param("to",endTime)
            .content("{\n"
                + "  \"page\": 0,\n"
                + "  \"size\": 1,\n"
                + "  \"sort\": [\n"
                + "    \"totalPrice\"\n"
                + "  ]\n"
                + "}"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("The orders get all"))
        .andExpect(jsonPath("$.data[0].totalPrice").value(2000))
        .andExpect(jsonPath("$.data[0].id").value(3));
  }
}
