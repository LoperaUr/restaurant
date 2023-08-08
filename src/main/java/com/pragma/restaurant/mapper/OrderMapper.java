package com.pragma.restaurant.mapper;

import com.pragma.restaurant.dto.order.OrderResponseDTO;
import com.pragma.restaurant.dto.orderDetail.OrderDetailDTO;
import com.pragma.restaurant.entity.Order;
import com.pragma.restaurant.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "menuList", qualifiedByName = "transformListMenuDetails")
    })

    OrderResponseDTO toDto(Order order);
    List<OrderResponseDTO> toDtoListOrder(List<Order> orderList);

    @Named("transformListMenuDetails")
    default List<OrderDetailDTO> transformListMenuDetails(List<OrderDetails> details) {
        return details.stream()
                .map(this::transformOrderDetails)
                .collect(Collectors.toList());
    }

    OrderDetailDTO transformOrderDetails(OrderDetails details);

}
