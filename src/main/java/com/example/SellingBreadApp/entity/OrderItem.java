package com.example.SellingBreadApp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "quantity")
    private int quantity;

    @Column(name = "priceItem")
    private double priceItem;


    @Column(name = "productName")
    private String productName;

    @Column(name = "productPriceUnit")
    private double productPriceUnit;

    public OrderItem() {
    }


    @OneToMany(mappedBy="orderItems")
    private List<OrderItemDetail> orderItemDetails;

    @ManyToOne
    @JoinColumn(name="orderId", nullable=false)
    private Orders orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(double priceItem) {
        this.priceItem = priceItem;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPriceUnit() {
        return productPriceUnit;
    }

    public void setProductPriceUnit(double productPriceUnit) {
        this.productPriceUnit = productPriceUnit;
    }

    public List<OrderItemDetail> getOrderItemDetails() {
        return orderItemDetails;
    }

    public void setOrderItemDetails(List<OrderItemDetail> orderItemDetails) {
        this.orderItemDetails = orderItemDetails;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public OrderItem(Long id, int quantity, double priceItem, String productName, double productPriceUnit, List<OrderItemDetail> orderItemDetails, Orders orders) {
        this.id = id;
        this.quantity = quantity;
        this.priceItem = priceItem;
        this.productName = productName;
        this.productPriceUnit = productPriceUnit;
        this.orderItemDetails = orderItemDetails;
        this.orders = orders;
    }
}

