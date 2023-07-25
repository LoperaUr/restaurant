package com.pragma.restaurant.dto.order;

public class OrderErrorDTO extends OrderDTO {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
