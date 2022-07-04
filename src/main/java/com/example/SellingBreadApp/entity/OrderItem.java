package com.example.SellingBreadApp.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "OrderItems")
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;


  @Column(name = "quantity")
  @Min(value = 1, message = "greater than 0")
  private Integer quantity;

  @Column(name = "priceItem")
  @Min(value = 1L, message = "greater than 0")
  private Double priceItem;


  @Column(name = "productName")
  private String productName;

  @Column(name = "productPriceUnit")
  @Min(value = 1L, message = "greater than 0")
  private Double productPriceUnit;


  @OneToMany(mappedBy = "orderItems", cascade = CascadeType.ALL)
  private List<OrderItemDetail> orderItemDetails;

  @ManyToOne
  @JoinColumn(name = "orderId", nullable = false)
  private Orders orders;

}

