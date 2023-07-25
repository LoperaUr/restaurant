package com.pragma.restaurant.dto.claim;

public class ClaimResponseDTO extends ClaimDTO{
    private Long id_order;

    private String restaurant;

    private String description;

    private String claim_state;

    private String reason;

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
