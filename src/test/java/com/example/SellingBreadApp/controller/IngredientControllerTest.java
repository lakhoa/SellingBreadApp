package com.example.SellingBreadApp.controller;
import com.example.SellingBreadApp.service.ProductService;
import com.example.SellingBreadApp.service.ToppingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = IngredientController.class)
class IngredientControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private  ToppingService toppingService;

  @MockBean
  private ProductService productService;

  @Test
  void shouldCreate() throws Exception{
    this.mockMvc
        .perform(MockMvcRequestBuilders.post("/createTopping")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"This is test\",\"price\":\"2.0d\"}")
        ).andExpect(MockMvcResultMatchers.status().isOk());

  }

}