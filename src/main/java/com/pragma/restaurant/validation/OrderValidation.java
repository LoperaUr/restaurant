package com.pragma.restaurant.validation;

import com.pragma.restaurant.entity.OrderDetails;
import com.pragma.restaurant.util.StateOrder;

import java.util.List;

public abstract class OrderValidation {
    public static Boolean validateRestaurantAndDetails(String restaurant, List<OrderDetails> menuList, StateOrder state) {
        return restaurant == null || menuList == null || state == null;
    }

    // metodo que valida que todos los platos de la orden sean del mismo restaurant
    public static Boolean validateRestaurantIsSame(String restaurant, String restaurantMenu) {

        if (!restaurant.equals(restaurantMenu)) {
            return false;
        }

        return true;
    }
}
