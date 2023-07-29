package com.pragma.restaurant.dto.client;

public class ClientErrorDTO extends ClientDTO{
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
