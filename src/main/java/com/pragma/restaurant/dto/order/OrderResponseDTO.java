package com.pragma.restaurant.dto.order;

import com.pragma.restaurant.dto.orderDetail.OrderDetailDTO;

import java.util.List;

public class OrderResponseDTO {
    private Long id;
    private String restaurant;
    private String orderState;
    private List<OrderDetailDTO> detailDTOS;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderDetailDTO> getMenuList() {
        return detailDTOS;
    }

    public void setMenuList(List<OrderDetailDTO> menuList) {
        this.detailDTOS = menuList;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
