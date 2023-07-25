package com.pragma.restaurant.mapper;

import com.pragma.restaurant.dto.menu.MenuResponseDTO;
import com.pragma.restaurant.entity.Menu;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuResponseDTO ToDto(Menu menu);

    List<MenuResponseDTO> toDtoList(List<Menu> menu);

}
