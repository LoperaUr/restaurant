package com.pragma.restaurant.repository;

import com.pragma.restaurant.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRespository extends JpaRepository<OrderDetails, Long> {
}
