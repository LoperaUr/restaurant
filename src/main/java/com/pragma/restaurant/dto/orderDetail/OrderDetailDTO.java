package com.pragma.restaurant.dto.orderDetail;

public class OrderDetailDTO {
    private String name;
    private Integer quantity;

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public Integer getquantity() {
        return quantity;
    }

    public void setquantity(Integer cantidad) {
        this.quantity = cantidad;
    }
}
