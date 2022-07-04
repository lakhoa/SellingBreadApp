package com.example.SellingBreadApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import lombok.Data;

@Entity
@Table(name = "Topping")
@Data
public class Topping {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @Column(name = "name", length = 100)
  private String name;
  @Column(name = "price")
  @Min(value = 1L, message = "greater than 0")
  private Double price;

}
