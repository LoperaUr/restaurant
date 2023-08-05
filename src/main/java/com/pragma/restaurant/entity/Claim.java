package com.pragma.restaurant.entity;

import com.pragma.restaurant.util.StateClaim;
import jakarta.persistence.*;

@Entity
@Table(name = "claim")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order orderId;

    private String restaurant;

    private String description;

    @Enumerated(EnumType.STRING)
    private StateClaim claim_state = StateClaim.PENDING;

    public Claim() {
    }

    public Claim(Long id, Order orderId, String restaurant, String description, StateClaim claim_state) {
        this.id = id;
        this.orderId = orderId;
        this.restaurant = restaurant;
        this.description = description;
        this.claim_state = claim_state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StateClaim getClaim_state() {
        return claim_state;
    }

    public void setClaim_state(StateClaim claim_state) {
        this.claim_state = claim_state;
    }
}

