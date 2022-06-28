package com.example.SellingBreadApp.controller;
import com.example.SellingBreadApp.entity.Product;
import com.example.SellingBreadApp.entity.Topping;
import com.example.SellingBreadApp.repository.ProductRepository;
import com.example.SellingBreadApp.repository.ToppingRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class IngredientControllerTest {
  private MockMvc mockMvc;
  @Autowired
  private ToppingRepository toppingRepository;
  @Autowired
  private IngredientController ingredientController;

  @Autowired
  private ProductRepository productRepository;


  @BeforeAll
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(ingredientController)
        .build();
  }
//get Data to Ready
  public Product initTopping(){
    Topping topping = new Topping();
    topping.setId(1L);
    topping.setName("Topping1");
    topping.setPrice(1000.0);
    toppingRepository.save(topping);

    List<Topping> toppings = new ArrayList<>();
    toppings.add(topping);
    Product product = new Product();
    product.setId(1L);
    product.setName("BBB");
    product.setMaxTopping(8);
    product.setToppings(toppings);
    return product;
  }
  @Test
  public void should_create_topping_without_error() throws Exception {
    mockMvc.perform(post("/createTopping")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Topping1\",\"price\":200}"))
        .andExpect(status().isOk());
  }
  @Test
  public void should_create_topping_fail_if_wrong_condition() throws Exception {
    mockMvc.perform(post("/createTopping")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Topping1\",\"price\":\"d\"}"))
        .andExpect(status().isBadRequest());
  }
}