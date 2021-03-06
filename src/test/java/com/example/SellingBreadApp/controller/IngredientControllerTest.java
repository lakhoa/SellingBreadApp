package com.example.SellingBreadApp.controller;
import com.example.SellingBreadApp.dto.ToppingDto;
import com.example.SellingBreadApp.entity.Product;
import com.example.SellingBreadApp.entity.Topping;
import com.example.SellingBreadApp.repository.ToppingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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



  @BeforeAll
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(ingredientController)
        .build();
    initDb();
  }

  //get Data to Ready
  void initDb() {
    Topping topping = new Topping();
    topping.setId(1L);
    topping.setName("Topping1");
    topping.setPrice(1000.0);
    toppingRepository.save(topping);
    List<Topping> toppings = new ArrayList<>();
    toppings.add(topping);
    Product product = new Product();
    product.setId(1L);
    product.setName("Product1");
    product.setMaxTopping(8);
    product.setToppings(toppings);
  }

  @Test
  void shouldCreateToppingWithoutError() throws Exception {
    ToppingDto toppingDto = new ToppingDto();
    toppingDto.setName("Topping2");
    toppingDto.setPrice(100.0);
    mockMvc.perform(post("/api/v1/topping")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(toppingDto)))
        .andExpect(status().isOk());
  }


  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
