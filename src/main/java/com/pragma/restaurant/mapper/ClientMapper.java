package com.pragma.restaurant.mapper;

import com.pragma.restaurant.dto.client.ClientResponseDTO;
import com.pragma.restaurant.dto.menu.MenuResponseDTO;
import com.pragma.restaurant.entity.Client;
import com.pragma.restaurant.entity.Menu;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

public interface ClientMapper {

    @Mappings({
            @Mapping(source ="nombre",target="nombre"),
            @Mapping(source ="orderUser",target="orderUser")

    })
    ClientResponseDTO ToDto(Client client);
    List<ClientResponseDTO> toDtoList(List<Client> client);
}
