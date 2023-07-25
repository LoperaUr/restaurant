package com.pragma.restaurant.dto.order;

import com.pragma.restaurant.entity.Menu;

import java.util.List;

public class OrderResponseDTO {
    private List<Menu> menuList;
    private String restaurant;
    private String orderState;

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
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
