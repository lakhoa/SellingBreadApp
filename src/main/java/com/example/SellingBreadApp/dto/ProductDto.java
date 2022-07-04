package com.example.SellingBreadApp.dto;

import javax.validation.constraints.Min;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductDto {

  private Long id;

  @NotNull
  private String name;

  @NotNull
  @Min(value = 1L, message = "greater than 0")
  private Double price;

  @NotNull
  @Min(value = 1, message = "greater than 0")
  private Integer maxTopping;

  private List<ToppingLinkDto> toppingItem;

}
