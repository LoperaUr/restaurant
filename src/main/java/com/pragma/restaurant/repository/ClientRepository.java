package com.pragma.restaurant.repository;

import com.pragma.restaurant.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
