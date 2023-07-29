package com.pragma.restaurant.dto.client;

public class ClientResponseDTO extends ClientDTO {
    private String nombre;
    private String orderUser;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }
}
