package com.pragma.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "order_active", nullable = false)
    private boolean orderActive = false;

    @OneToMany(mappedBy = "userOrder")
    @JsonManagedReference
    @JsonIgnore
    private List<Order> orderUser;

    public Client() {
    }

    public Client(Long id, String name, boolean orderActive, List<Order> orderUser) {
        this.id = id;
        this.name = name;
        this.orderActive = orderActive;
        this.orderUser = orderUser;
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

    public boolean isOrderActive() {
        return orderActive;
    }

    public void setOrderActive(boolean orderActive) {
        this.orderActive = orderActive;
    }

    public List<Order> getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(List<Order> orderUser) {
        this.orderUser = orderUser;
    }
}
