package com.pragma.restaurant.validation;

import com.pragma.restaurant.entity.Order;
import com.pragma.restaurant.entity.OrderDetails;

import java.util.List;

public abstract class OrderValidation {
    public static Boolean validateRestaurantAndDetails(Order order) {
        return order.getRestaurant() == null || order.getRestaurant().isEmpty() ||
                order.getOrderState() == null || order.getMenuList().isEmpty();
    }

    // metodo que valida que todos los platos de la orden sean del mismo restaurant
    public static Boolean validateRestaurantIsSame(Order order) {
        String restaurantVal = order.getRestaurant();
        List<OrderDetails> menuList = order.getMenuList();
        for (OrderDetails detail : menuList) {
            if (!detail.getMenuId().getRestaurant().equals(restaurantVal)) {
                return false;
            }
        }
        return true;
    }
}
