package com.example.SellingBreadApp.entity;
import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "OrderItemDetail")
public class OrderItemDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;

    @Column(name = "quantity")
    @Min(value = 1, message = "greater than 0")
    private Integer quantityTopping;

    @Column(name = "toppingPriceUnit")
    @Min(value = 1L, message = "greater than 0")
    private Double toppingPriceUnit;

    @Column(name = "toppingName")
    private String toppingName;

    public OrderItemDetail() {
    }
    @ManyToOne
    @JoinColumn(name="orderItemId", nullable=false)
    private OrderItem orderItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantityTopping() {
        return quantityTopping;
    }

    public void setQuantityTopping(Integer quantityTopping) {
        this.quantityTopping = quantityTopping;
    }

    public Double getToppingPriceUnit() {
        return toppingPriceUnit;
    }

    public void setToppingPriceUnit(Double toppingPriceUnit) {
        this.toppingPriceUnit = toppingPriceUnit;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public OrderItem getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItem orderItems) {
        this.orderItems = orderItems;
    }

    public OrderItemDetail(Long id, Integer quantityTopping, Double toppingPriceUnit, String toppingName, OrderItem orderItems) {
        this.id = id;
        this.quantityTopping = quantityTopping;
        this.toppingPriceUnit = toppingPriceUnit;
        this.toppingName = toppingName;
        this.orderItems = orderItems;
    }
}

