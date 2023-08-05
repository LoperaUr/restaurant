package com.pragma.restaurant.dto.employee;

import com.pragma.restaurant.entity.Order;

import java.util.List;

public class EmployeeResponseDTO extends EmployeeDTO {
     private long id;
        private String name;
        private List<Order> orders;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
