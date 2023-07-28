package com.pragma.restaurant.validation;

import com.pragma.restaurant.entity.Order;

public abstract class OrderValidation {
    public static Boolean validateRestaurantAndDetails(Order order) {
        return order.getRestaurant() == null || order.getRestaurant().isEmpty() ||
                order.getOrderState() == null || order.getMenuList().isEmpty();
    }

    /* public static Boolean validateRestaurantIsSame(Order order, String restaurant) {
        return !order.getRestaurant().equals(restaurant);
    }*/

}
