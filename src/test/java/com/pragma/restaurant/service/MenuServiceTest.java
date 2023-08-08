package com.pragma.restaurant.service;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.pragma.restaurant.dto.menu.MenuResponseDTO;
import com.pragma.restaurant.entity.Menu;
import com.pragma.restaurant.mapper.MenuMapper;
import com.pragma.restaurant.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuServiceTest {

    private MenuService menuService;
    private MenuRepository menuRepositoryMock;
    private MenuMapper menuMapperMock;

    @BeforeEach
    public void setUp() {
        menuRepositoryMock = mock(MenuRepository.class);
        menuMapperMock = mock(MenuMapper.class);

        menuService = new MenuService(
                menuRepositoryMock,
                menuMapperMock
        );
    }

    @Test
    public void testSearchAll() throws Exception {
        // Crear una lista ficticia de menús
        List<Menu> menus = new ArrayList<>();
        // Agregar menús ficticios a la lista...

        // Configurar el comportamiento simulado para menuRepository.findAll
        when(menuRepositoryMock.findAll()).thenReturn(menus);

        // Configurar el comportamiento simulado para menuMapper.toDtoList
        List<MenuResponseDTO> dtoList = new ArrayList<>();
        // Agregar MenuResponseDTO ficticios a la lista...
        when(menuMapperMock.toDtoList(anyList())).thenReturn(dtoList);

        // Llamar al método que se está probando
        List<MenuResponseDTO> result = menuService.searchAll();

        // Verificar el comportamiento esperado
        verify(menuRepositoryMock, times(1)).findAll();
        verify(menuMapperMock, times(1)).toDtoList(eq(menus));
        // Verificar que el resultado no sea nulo
        // Por ejemplo:
        // assertNotNull(result);
    }
}