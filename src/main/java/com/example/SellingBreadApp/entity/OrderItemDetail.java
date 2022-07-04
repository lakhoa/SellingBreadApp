package com.example.SellingBreadApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "OrderItemDetail")
public class OrderItemDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "quantity")
  @Min(value = 1, message = "greater than 0")
  private Integer quantityTopping;

  @Column(name = "toppingPriceUnit")
  @Min(value = 1L, message = "greater than 0")
  private Double toppingPriceUnit;

  @Column(name = "toppingName")
  private String toppingName;

  @ManyToOne
  @JoinColumn(name = "orderItemId", nullable = false)
  private OrderItem orderItems;

}

