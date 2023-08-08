package com.pragma.restaurant.dto.claim;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pragma.restaurant.entity.Order;

public class ClaimResponseDTO extends ClaimDTO{

    private Long id;
    private Order orderId;

    private String restaurant;

    private String description;

    private String claimState;

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

    public String getClaimState() {
        return claimState;
    }

    public void setClaimState(String claimState) {
        this.claimState = claimState;
    }
}
