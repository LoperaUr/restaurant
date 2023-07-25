package com.pragma.restaurant.dto;

public class MenuErrorDTO extends MenuDTO {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
