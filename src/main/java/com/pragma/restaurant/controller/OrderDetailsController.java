package com.pragma.restaurant.controller;

import com.pragma.restaurant.dto.orderDetail.OrderDetailDTO;
import com.pragma.restaurant.dto.orderDetail.OrderDetailErrorDTO;
import com.pragma.restaurant.entity.OrderDetails;
import com.pragma.restaurant.service.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order-details")
public class OrderDetailsController {
    private final OrderDetailService orderDetailService;

    public OrderDetailsController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping
    ResponseEntity<OrderDetailDTO> create(@RequestBody OrderDetails data) {
        try {
            return ResponseEntity
                    .ok()
                    .body(orderDetailService.create(data));
        } catch (Exception e) {
            OrderDetailErrorDTO resError = new OrderDetailErrorDTO();
            resError.setError(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(resError);
        }
    }

}
