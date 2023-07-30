package com.pragma.restaurant.mapper;

import com.pragma.restaurant.dto.client.ClientResponseDTO;
import com.pragma.restaurant.dto.orderDetail.OrderDetailDTO;
import com.pragma.restaurant.entity.Client;
import com.pragma.restaurant.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponseDTO ToDto(Client client);
    List<ClientResponseDTO> toDtoList(List<Client> client);

    @Named("transformListOrderDetails")
    default List<OrderDetailDTO> transformListOrderDetails(List<OrderDetails> details) {
        return details.stream()
                .map(this::transformOrderDetails)
                .collect(Collectors.toList());
    }

    OrderDetailDTO transformOrderDetails(OrderDetails details);

}
