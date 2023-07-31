package com.pragma.restaurant.dto.client;

import com.pragma.restaurant.entity.Order;

import java.util.List;

public class ClientResponseDTO extends ClientDTO {

    private String name;
    private List<Order> orderUser;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(List<Order> orderUser) {
        this.orderUser = orderUser;
    }
}
