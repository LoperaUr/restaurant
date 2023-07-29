package com.pragma.restaurant.repository;

import com.pragma.restaurant.entity.Client;
import com.pragma.restaurant.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
