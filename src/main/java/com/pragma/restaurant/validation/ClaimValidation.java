package com.pragma.restaurant.validation;

public abstract class ClaimValidation {

    public static Boolean validateDescription(String description) {
        return description == null || description.isEmpty();
    }



}
