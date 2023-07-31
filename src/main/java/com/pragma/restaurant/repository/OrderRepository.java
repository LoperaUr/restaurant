package com.pragma.restaurant.repository;

import com.pragma.restaurant.entity.Client;
import com.pragma.restaurant.entity.Order;
import com.pragma.restaurant.util.StateOrder;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByRestaurantAndOrderState(String restaurant, StateOrder stateOrder, Pageable pageable);


    Order findByUserOrder(Client id);
}
