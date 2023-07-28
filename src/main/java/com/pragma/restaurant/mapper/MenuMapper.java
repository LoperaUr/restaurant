package com.pragma.restaurant.mapper;

import com.pragma.restaurant.dto.menu.MenuResponseDTO;
import com.pragma.restaurant.entity.Menu;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuResponseDTO ToDto(Menu menu);

    List<MenuResponseDTO> toDtoList(List<Menu> menu);

    Page<MenuResponseDTO> toDtoPage(Page<Menu> menus);
}
