package com.pragma.restaurant.mapper;


import com.pragma.restaurant.dto.orderDetail.OrderDetailResponseDTO;
import com.pragma.restaurant.entity.OrderDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {

    OrderDetailResponseDTO toDto(OrderDetails orderDetails);

    List<OrderDetailResponseDTO> toDtoListOrderDetails(List<OrderDetails> orderDetailsList);


}
