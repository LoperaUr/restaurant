package com.pragma.restaurant.dto.claim;

import com.pragma.restaurant.entity.Order;

public class ClaimResponseDTO extends ClaimDTO{

    private Long id;
    private Order orderId;

    private String restaurant;

    private String description;

    private String claim_state;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getIdOrder() {
        return orderId;
    }

    public void setIdOrder(Order id_order) {
        this.orderId = id_order;
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

    public String getClaim_state() {
        return claim_state;
    }

    public void setClaim_state(String claim_state) {
        this.claim_state = claim_state;
    }

}
