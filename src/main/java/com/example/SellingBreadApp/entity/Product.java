package com.example.SellingBreadApp.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import lombok.Data;

@Entity
@Table(name = "Product")
@Data
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "price")
  @Min(value = 1L, message = "greater than 0")
  private Double price;

  @Column(name = "maxTopping")
  @Min(value = 1, message = "greater than 0")
  private Integer maxTopping;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "ProductTopping",
      joinColumns = {@JoinColumn(name = "productId")},
      inverseJoinColumns = {@JoinColumn(name = "toppingId")})
  private List<Topping> toppings = new ArrayList<>();
}

