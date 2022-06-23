package com.example.SellingBreadApp.entity;

import javax.persistence.*;

@Entity
@Table(name = "OrderItemDetail")
public class OrderItemDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;

    @Column(name = "quantity")
    private int quantityTopping;

    @Column(name = "toppingPriceUnit")
    private double toppingPriceUnit;

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

    public int getQuantityTopping() {
        return quantityTopping;
    }

    public void setQuantityTopping(int quantityTopping) {
        this.quantityTopping = quantityTopping;
    }

    public double getToppingPriceUnit() {
        return toppingPriceUnit;
    }

    public void setToppingPriceUnit(double toppingPriceUnit) {
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

    public OrderItemDetail(Long id, int quantityTopping, double toppingPriceUnit, String toppingName, OrderItem orderItems) {
        this.id = id;
        this.quantityTopping = quantityTopping;
        this.toppingPriceUnit = toppingPriceUnit;
        this.toppingName = toppingName;
        this.orderItems = orderItems;
    }
}

