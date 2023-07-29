package com.pragma.restaurant.mapper;

import com.pragma.restaurant.dto.menu.MenuResponseDTO;
import com.pragma.restaurant.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mappings({
            @Mapping(source ="price",target="price"),
            @Mapping(source ="description",target="description"),
            @Mapping(source ="image",target="image"),
    })

    MenuResponseDTO ToDto(Menu menu);
    List<MenuResponseDTO> toDtoList(List<Menu> menu);
}
