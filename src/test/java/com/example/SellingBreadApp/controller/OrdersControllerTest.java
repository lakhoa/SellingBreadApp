/*File for only test stream create order*/
package com.example.SellingBreadApp.controller;
import com.example.SellingBreadApp.dto.OrderItemDetailRequestDTO;
import com.example.SellingBreadApp.dto.OrderItemRequestDTO;
import com.example.SellingBreadApp.dto.OrderRequestDTO;
import com.example.SellingBreadApp.entity.Orders;
import com.example.SellingBreadApp.entity.Product;
import com.example.SellingBreadApp.entity.Topping;
import com.example.SellingBreadApp.exception.ExceptionControllerAdvice;
import com.example.SellingBreadApp.repository.OrdersRepository;
import com.example.SellingBreadApp.repository.ProductRepository;
import com.example.SellingBreadApp.repository.ToppingRepository;
import com.example.SellingBreadApp.service.OrdersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.ObjectMapperFactory;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
class OrdersControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private ExceptionControllerAdvice exceptionControllerAdvice;
  @Autowired
  private PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver;

  @Autowired
  private OrdersService ordersService;
  @Autowired
  private ToppingRepository toppingRepository;
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrdersRepository ordersRepository;
  @Autowired
  private OrderController orderController;


  @Autowired
  private  IngredientController ingredientController;


  public List<Product> initIngredient(){
    Topping topping1 = new Topping();
    Topping topping2 = new Topping();
    Topping topping3 = new Topping();
    Product product1 = new Product();
    Product product2 = new Product();
    topping1.setName("Cha lua");
    topping1.setPrice(10.0);

    topping2.setName("Trung");
    topping2.setPrice(7.0);

    topping3.setName("Dua leo");
    topping3.setPrice(4.0);

    Topping topping1Save = toppingRepository.save(topping1);
    Topping topping2Save = toppingRepository.save(topping2);
    Topping topping3Save = toppingRepository.save(topping3);

    List<Topping> toppings1 = new ArrayList<>();
    toppings1.add(topping1Save);
    toppings1.add(topping2Save);
    toppings1.add(topping3Save);

    List<Topping> toppings2 = new ArrayList<>();
    toppings2.add(topping1Save);
    toppings2.add(topping2Save);

    product1.setName("Banh mi");
    product1.setPrice(8.0);
    product1.setMaxTopping(8);
    product1.setToppings(toppings1);

    product2.setName("Hamburger");
    product2.setPrice(9.0);
    product2.setMaxTopping(5);
    product2.setToppings(toppings2);

    Product product1Save = productRepository.save(product1);
    Product product2Save = productRepository.save(product2);

    List<Product> productList = new ArrayList<>();
    productList.add(product1Save);
    productList.add(product2Save);
    return productList;
  }

  public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
      MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

  private String createRequestDTO(List<Product> productList,Long productId, Long toppingId, Integer quantityTopping)
      throws JsonProcessingException {
    Product product1 = productList.get(0);
    Topping topping1 = product1.getToppings().get(0);
    Topping topping2 = product1.getToppings().get(1);

    OrderItemDetailRequestDTO orderItemDetailRequestDTO1 = new OrderItemDetailRequestDTO(topping1.getId(), 1);
    OrderItemDetailRequestDTO orderItemDetailRequestDTO2 = new OrderItemDetailRequestDTO(toppingId, quantityTopping);
    OrderItemDetailRequestDTO orderItemDetailRequestDTO3 = new OrderItemDetailRequestDTO(topping2.getId(), 1);

    List<OrderItemDetailRequestDTO> orderItemDetailRequestDTOList1 = new ArrayList<>();
    orderItemDetailRequestDTOList1.add(orderItemDetailRequestDTO1);
    orderItemDetailRequestDTOList1.add(orderItemDetailRequestDTO2);
    orderItemDetailRequestDTOList1.add(orderItemDetailRequestDTO3);

    List<OrderItemDetailRequestDTO> orderItemDetailRequestDTOList2 = new ArrayList<>();
    orderItemDetailRequestDTOList2.add(orderItemDetailRequestDTO1);
    orderItemDetailRequestDTOList2.add(orderItemDetailRequestDTO2);

    List<OrderItemDetailRequestDTO> orderItemDetailRequestDTOList3 = new ArrayList<>();

    //topping item1 exception invalid sum and topping cannot add
    OrderItemRequestDTO orderRequestDTO1 = new OrderItemRequestDTO();
    orderRequestDTO1.setProductId(product1.getId());
    orderRequestDTO1.setQuantityItem(5);
    orderRequestDTO1.setItemRequestDTOList(orderItemDetailRequestDTOList1);
    // cannot find product
    OrderItemRequestDTO orderRequestDTO2 = new OrderItemRequestDTO();
    orderRequestDTO2.setProductId(productId);
    orderRequestDTO2.setQuantityItem(5);
    orderRequestDTO2.setItemRequestDTOList(orderItemDetailRequestDTOList2);
    // list empty
    OrderItemRequestDTO orderRequestDTO3 = new OrderItemRequestDTO();
    orderRequestDTO3.setProductId(product1.getId());
    orderRequestDTO3.setQuantityItem(5);
    orderRequestDTO3.setItemRequestDTOList(orderItemDetailRequestDTOList3);

    List<OrderItemRequestDTO> orderItemRequestDTOList = new ArrayList<>();
    orderItemRequestDTOList.add(orderRequestDTO1);
    orderItemRequestDTOList.add(orderRequestDTO2);
    orderItemRequestDTOList.add(orderRequestDTO3);

    OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
    orderRequestDTO.setOrderItemRequestDTOList(orderItemRequestDTOList);

    ObjectMapper objectMapper = ObjectMapperFactory.buildStrictGenericObjectMapper();
    return objectMapper.writeValueAsString(orderRequestDTO);
  }

  //  @ParameterizedTest
//  @MethodSource("createOrderParamTest")
  @BeforeEach
  public void setUp() {
    toppingRepository.deleteAll();
    productRepository.deleteAll();
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(orderController)
        .setControllerAdvice(exceptionControllerAdvice)
        .setCustomArgumentResolvers(pageableHandlerMethodArgumentResolver)
        .build();
  }
  @Test
  public void shouldReturnBillWhenCreateOrderRight () throws Exception {

    List<Product> productList = initIngredient();
    Product product1 = productList.get(0);
    Product product2 = productList.get(1);
    Topping topping3 = product1.getToppings().get(2);
    Topping topping2 = product1.getToppings().get(1);
    String requestDTO = createRequestDTO(productList,product1.getId(), topping3.getId(), 1);
    mockMvc.perform(post("/api/v1/order")
            .contentType(APPLICATION_JSON_UTF8).content(requestDTO))
        .andDo(MockMvcResultHandlers.print())
        .andExpect((status().isOk()))
        .andExpect(jsonPath("$.data.orderItemResponseDTOList[:1].productName").value(product1.getName()))
        .andExpect(jsonPath("$.data.orderItemResponseDTOList[:1].productPriceUnit").value(product1.getPrice()))
        .andExpect(jsonPath("$.data.orderItemResponseDTOList[1].orderItemDetailResponseDTOList[1].toppingName").value(topping3.getName()))
        .andExpect(jsonPath("$.data.totalPrice").value(295.0));

    PageRequest.of(0, 1);
    Orders response = ordersRepository.findAll(PageRequest.of(0, 1)).get().findFirst().get();
    Assertions.assertEquals(response.getTotalPrice(), 295.0);
    Assertions.assertEquals(response.getOrderItems().get(0).getProductName(), product1.getName());
  }
  @Test // product2 no have link to topping3
  public void shouldReturnCannotAddToppingExceptionWhenCreateOrderWithWrongTopping () throws Exception {
    List<Product> productList = initIngredient();
    Product product1 = productList.get(0);
    Product product2 = productList.get(1);
    Topping topping3 = product1.getToppings().get(2);
    String requestDTO = createRequestDTO(productList, product2.getId(), topping3.getId(), 1);
    MvcResult result = mockMvc.perform(post("/api/v1/order")
            .contentType(APPLICATION_JSON_UTF8).content(requestDTO))
        .andDo(MockMvcResultHandlers.print())
        .andExpect((status().isBadRequest()))
        .andReturn();
    String content = result.getResponse().getContentAsString();
    Assertions.assertEquals(content, "Invalid toppingId to add in product with toppingId 3");

  }
  @Test //-1L is not a id of product
  public void shouldReturnCannotFindProductWhenCreateOrderWithWrongProductId () throws Exception {
    List<Product> productList = initIngredient();
    Product product1 = productList.get(0);
    Topping topping3 = product1.getToppings().get(2);
    String requestDTO = createRequestDTO(productList,-1L, topping3.getId(), 1);
    MvcResult result = mockMvc.perform(post("/api/v1/order")
            .contentType(APPLICATION_JSON_UTF8).content(requestDTO))
        .andDo(MockMvcResultHandlers.print())
        .andExpect((status().isBadRequest()))
        .andReturn();
    String content = result.getResponse().getContentAsString();
    Assertions.assertEquals(content, "Cannot find product with productId : -1");
  }
  @Test //product cannot add maxTopping + 1
  public void shouldReturnInvalidSumToppingWhenCreateOrderWithOverQuantity () throws Exception {
    List<Product> productList = initIngredient();
    Product product1 = productList.get(0);
    Topping topping3 = product1.getToppings().get(2);
    String requestDTO = createRequestDTO(productList, product1.getId(), topping3.getId(), product1.getMaxTopping()+1);
    MvcResult result = mockMvc.perform(post("/api/v1/order")
            .contentType(APPLICATION_JSON_UTF8).content(requestDTO))
        .andDo(MockMvcResultHandlers.print())
        .andExpect((status().isBadRequest()))
        .andReturn();
    String content = result.getResponse().getContentAsString();
    Assertions.assertEquals(content, "Invalid: Banh mi only have 8 toppings");
  }
}