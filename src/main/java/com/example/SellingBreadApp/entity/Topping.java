package com.example.SellingBreadApp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Topping")
public class Topping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private  Double price;

    public Topping() {
    }


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

    public Topping(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}


