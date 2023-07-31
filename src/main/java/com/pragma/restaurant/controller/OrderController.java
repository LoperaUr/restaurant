package com.pragma.restaurant.controller;

import com.pragma.restaurant.dto.order.OrderDTO;
import com.pragma.restaurant.dto.order.OrderErrorDTO;
import com.pragma.restaurant.dto.order.OrderResponseDTO;
import com.pragma.restaurant.entity.Order;
import com.pragma.restaurant.service.OrderService;
import com.pragma.restaurant.util.StateOrder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/")
    ResponseEntity<OrderDTO> create(@RequestBody Order data) {
        try {
            return ResponseEntity
                    .ok()
                    .body(orderService.create(data));
        } catch (Exception e) {
            OrderErrorDTO resError = new OrderErrorDTO();
            resError.setError(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(resError);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<OrderDTO> update(@PathVariable Long id, @RequestBody Order data) {
        try {
            return ResponseEntity
                    .ok()
                    .body(orderService.update(id, data));
        } catch (Exception e) {
            OrderErrorDTO resError = new OrderErrorDTO();
            resError.setError(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(resError);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> delete(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok()
                    .body(orderService.delete(id));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(false);
        }
    }

    @GetMapping("/")
    ResponseEntity<List<OrderResponseDTO>> filterByStateAndRestaurant(
            @RequestParam Character rol,
            @RequestParam String restaurant,
            @RequestParam StateOrder state,
            @RequestParam int size
    ) {
        try {
            Page<OrderResponseDTO> pageOrders = orderService.getListOrdersByStateAndRestaurant(rol, restaurant,  state, size);
            List<OrderResponseDTO> orders = pageOrders.getContent();

            return ResponseEntity
                    .ok()
                    .body(orders);
        } catch (Exception e) {
            OrderErrorDTO resError = new OrderErrorDTO();
            resError.setError(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }
}
