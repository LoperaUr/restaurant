package com.pragma.restaurant.mapper;

import com.pragma.restaurant.dto.order.OrderResponseDTO;
import com.pragma.restaurant.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {



    //oeoeoe escribí los mappers siguiendo el codigo del profe
    //https://github.com/jjosegallegocesde/retopragmaceibafinal/blob/main/src/main/java/com/example/restaurantefinal/mapas/PedidoMapa.java
    //tendriamos que implementar la entidad "OrderDetails" detalles del pedido

    @Mappings({
            @Mapping(source="id",target="id"),
            @Mapping(source="restaurant",target="restaurant"),
            @Mapping(source="state",target="state"),

            @Mapping(target = "details",qualifiedByName = "transformListMenuDetails")
    })






    OrderResponseDTO toDto(Order order);
    List<OrderResponseDTO> toDtoListOrder(List<Order>orderList);



    @Named("transformListMenuDetails")
    default List<OrderDetailsDTO>transformListOrderDetails(List<DetailsOrder>details){
        return details.stream()
                .map(this::transformListOrderDetails)
                .collect(Collectors.toList());
    }

    @Mapping(target="name",source="Menu.MenuName")
    @Mapping(target="cantidad",source="cantidad")
    OrderDetailsDTO transformOrderDetails(OrderDetails details);


}
