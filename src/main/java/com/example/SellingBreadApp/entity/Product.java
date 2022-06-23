package com.example.SellingBreadApp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private  double price;

    @Column(name = "maxTopping")
    private int maxTopping;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ProductTopping",
            joinColumns = { @JoinColumn(name = "productId") },
            inverseJoinColumns = { @JoinColumn(name = "toppingId") })
    private List<Topping> toppings = new ArrayList<>();

    public Product() {
    }

    public int getMaxTopping() {
        return maxTopping;
    }

    public void setMaxTopping(int maxTopping) {
        this.maxTopping = maxTopping;
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


    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public Product(Long id, String name, double price, int maxTopping, List<Topping> toppings) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.maxTopping = maxTopping;
        this.toppings = toppings;
    }
}

