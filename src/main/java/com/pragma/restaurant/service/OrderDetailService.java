package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.orderDetail.OrderDetailResponseDTO;
import com.pragma.restaurant.entity.OrderDetails;
import com.pragma.restaurant.mapper.OrderDetailsMapper;
import com.pragma.restaurant.repository.OrderDetailRespository;
import com.pragma.restaurant.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {

    private final OrderDetailsMapper orderDetailsMapper;

    private final OrderDetailRespository orderDetailRespository;


    public OrderDetailService(OrderDetailsMapper orderDetailsMapper, OrderDetailRespository orderDetailRespository) {
        this.orderDetailsMapper = orderDetailsMapper;
        this.orderDetailRespository = orderDetailRespository;
    }

    public OrderDetailResponseDTO create(OrderDetails data) throws Exception {
        try {
            return orderDetailsMapper.toDto(orderDetailRespository.save(data));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
