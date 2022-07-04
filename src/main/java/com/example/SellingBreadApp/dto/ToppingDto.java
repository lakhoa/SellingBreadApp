package com.example.SellingBreadApp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ToppingDto {

  private Long id;
  @NotNull
  private String name;
  @NotNull
  @Min(value = 1L, message = "greater than 0")
  private Double price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public ToppingDto() {
  }
}
