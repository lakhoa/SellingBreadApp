package com.example.SellingBreadApp.controller;
import com.example.SellingBreadApp.entity.Product;
import com.example.SellingBreadApp.entity.Topping;
import com.example.SellingBreadApp.exception.ExceptionControllerAdvice;
import com.example.SellingBreadApp.repository.OrdersRepository;
import com.example.SellingBreadApp.repository.ProductRepository;
import com.example.SellingBreadApp.repository.ToppingRepository;
import com.example.SellingBreadApp.service.OrdersService;
import com.example.SellingBreadApp.service.ProductService;
import com.example.SellingBreadApp.service.ToppingService;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

  @BeforeAll
  public void setUp(){
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(orderController)
        .setCustomArgumentResolvers(pageableHandlerMethodArgumentResolver)
        .setControllerAdvice(exceptionControllerAdvice)
        .build();
  }
  @Test
  public void should_create_orders_without_error() throws Exception{
    //Create data
    Topping topping = new Topping();
    topping.setName("Topping2");
    topping.setPrice(1000.0);
    toppingRepository.save(topping);
    List<Topping> toppings = new ArrayList<>();
    toppings.add(topping);
    Product product = new Product();
    product.setName("FFF");
    product.setPrice(1000.0d);
    product.setMaxTopping(2);
    product.setToppings(toppings);
    productRepository.save(product);
    mockMvc.perform(post("/order")
        .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                    + "  \"orderItemRequestDTOList\": [\n"
                    + "    {\n"
                    + "      \"productId\": 3,\n"
                    + "      \"quantityItem\": 1,\n"
                    + "      \"itemRequestDTOList\": [\n"
                    + "        {\n"
                    + "          \"toppingId\": 3,\n"
                    + "          \"quantityTopping\": 1\n"
                    + "        }\n"
                    + "      ]\n"
                    + "    }\n"
                    + "  ]\n"
                    + "}")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("The order is added"))
        .andExpect(jsonPath("$.data.totalPrice").value(2000));
  }
  @Test
  public void should_create_orders_with_error_if_wrong_condition_of_field() throws Exception{
    //Create data
    Topping topping = new Topping();
    topping.setName("Topping1");
    topping.setPrice(1000.0);
    toppingRepository.save(topping);
    List<Topping> toppings = new ArrayList<>();
    toppings.add(topping);
    Product product = new Product();
    product.setName("BBB");
    product.setPrice(1000.0d);
    product.setMaxTopping(2);
    product.setToppings(toppings);
    productRepository.save(product);
    mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                    + "  \"orderItemRequestDTOList\": [\n"
                    + "    {\n"
                    + "      \"productId\": 2,\n"
                    + "      \"quantityItem\": 1,\n"
                    + "      \"itemRequestDTOList\": [\n"
                    + "        {\n"
                    + "          \"toppingId\": 2,\n"
                    + "          \"quantityTopping\": 1\n"
                    + "        }\n"
                    + "      ]\n"
                    + "    \n"
                    + "  ]\n"
                    + "}")
            //.content("{\"orderItemRequestDTOList\":\"[\"{\"productId\": \"1\",\"quantityItem\": \"1\",\"itemRequestDTOList\":\"[\"{\"toppingId\": \"1\",\"quantityTopping\": \"1\"}\"]\"}\"]\"}")
        )
        .andExpect(status().isBadRequest());
  }

  @Test
  public void get_orders_list() throws Exception{
    mockMvc.perform(get("/orderList")
        .content("{\n"
            + "  \"page\": 0,\n"
            + "  \"size\": 1,\n"
            + "  \"sort\": [\n"
            + "    \"totalPrice\"\n"
            + "  ]\n"
            + "}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("The orders get all"))
        .andExpect(jsonPath("$.data").isArray());
  }

  @Test
  public void get_orders_list_with_id_without_error() throws Exception {
    Topping topping = new Topping();
    topping.setName("Topping2");
    topping.setPrice(1000.0d);
    toppingRepository.save(topping);
    List<Topping> toppings = new ArrayList<>();
    toppings.add(topping);
    Product product = new Product();
    product.setName("FFF");
    product.setPrice(1000.0d);
    product.setMaxTopping(2);
    product.setToppings(toppings);
    productRepository.save(product);
    mockMvc.perform(post("/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n"
            + "  \"orderItemRequestDTOList\": [\n"
            + "    {\n"
            + "      \"productId\": 1,\n"
            + "      \"quantityItem\": 1,\n"
            + "      \"itemRequestDTOList\": [\n"
            + "        {\n"
            + "          \"toppingId\": 1,\n"
            + "          \"quantityTopping\": 1\n"
            + "        }\n"
            + "      ]\n"
            + "    }\n"
            + "  ]\n"
            + "}")
    );
    mockMvc.perform(get("/order/{id}" , 1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("The order detail is get"))
        .andExpect(jsonPath("$.data.totalPrice").value(2000));
  }
}
