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
    private  double price;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Topping(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}


