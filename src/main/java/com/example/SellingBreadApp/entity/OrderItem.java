package com.example.SellingBreadApp.entity;
import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.Min;

@Entity
@Table(name = "OrderItem")
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
    @Min(value = 1L,message = "greater than 0")
    private Double productPriceUnit;

    public OrderItem() {
    }

    @OneToMany(mappedBy="orderItems", cascade = CascadeType.ALL)
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(Double priceItem) {
        this.priceItem = priceItem;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPriceUnit() {
        return productPriceUnit;
    }

    public void setProductPriceUnit(Double productPriceUnit) {
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

    public OrderItem(Long id, Integer quantity, Double priceItem, String productName, Double productPriceUnit, Orders orders) {
        this.id = id;
        this.quantity = quantity;
        this.priceItem = priceItem;
        this.productName = productName;
        this.productPriceUnit = productPriceUnit;
        this.orders = orders;
    }
}

