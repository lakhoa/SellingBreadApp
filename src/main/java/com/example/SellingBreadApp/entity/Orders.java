package com.example.SellingBreadApp.entity;
import java.util.Date;
import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.Min;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;

    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @Column(name = "createDate")
    private Date createDate;

    @Temporal(TemporalType.TIME)
    @CreationTimestamp
    @Column(name = "createTime")
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "createDateTime")
    private Date createDateTime;

    @Column(name = "totalPrice")
    @Min(value = 1L, message = "greater than 0")
    private Double totalPrice;



    public Orders() {

    }

    public Orders(Long id, Date createDate, Date createdTime, Date createDateTime,
        Double totalPrice) {
        this.id = id;
        this.createDate = createDate;
        this.createdTime = createdTime;
        this.createDateTime = createDateTime;
        this.totalPrice = totalPrice;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


    @OneToMany(mappedBy="orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}


