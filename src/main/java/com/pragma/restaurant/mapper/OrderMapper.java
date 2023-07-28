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
            @Mapping(source="id",target="id"),
            @Mapping(source="restaurant",target="restaurant"),
            @Mapping(source="state",target="state"),

            @Mapping(target = "details",qualifiedByName = "transformListMenuDetails")
    })

    OrderResponseDTO toDto(Order order);
    List<OrderResponseDTO> toDtoListOrder(List<Order>orderList);

    @Named("transformListMenuDetails")
    default List<OrderDetailDTO>transformListOrderDetails(List<OrderDetails>details){
        return details.stream()
                .map(this::transformOrderDetails)
                .collect(Collectors.toList());
    }

    @Mapping(target="name",source="Menu.MenuName")
    @Mapping(target="cantidad",source="cantidad")
    OrderDetailDTO transformOrderDetails(OrderDetails details);

}
