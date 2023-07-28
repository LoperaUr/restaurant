package com.pragma.restaurant.validation;

import com.pragma.restaurant.repository.MenuRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class MenuValidation {
    
    private final MenuValidation menuValidation;
    private final MenuRepository menuRepository;

    protected MenuValidation(MenuValidation menuValidation, MenuRepository menuRepository) {
        this.menuValidation = menuValidation;
        this.menuRepository = menuRepository;
    }

    public static boolean validatePrice(Integer price) {
        return price > 0;
    }
    
    public static boolean validateRestaurant(String restaurant) {
        return restaurant != null && !restaurant.isEmpty();
    }



}
