package com.pragma.restaurant.mapper;

import com.pragma.restaurant.dto.order.OrderResponseDTO;
import com.pragma.restaurant.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponseDTO toDto(Order order);
    List<OrderResponseDTO> toDtoListOrder(List<Order>orderList);


}
