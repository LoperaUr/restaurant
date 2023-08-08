package com.pragma.restaurant.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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

    @Test
    public void testCreateMenuWithValidData() throws Exception {
        // Crear un objeto Menu ficticio para la prueba
        Menu menu = new Menu();
        menu.setRol('A');
        menu.setPrice(10);
        menu.setRestaurant("Restaurante A");

        // Configurar el comportamiento simulado para menuRepository.save
        when(menuRepositoryMock.save(any(Menu.class))).thenReturn(menu);

        // Configurar el comportamiento simulado para menuMapper.ToDto
        MenuResponseDTO menuResponseDTO = new MenuResponseDTO();
        // Configurar el comportamiento simulado para menuMapper.ToDto
        when(menuMapperMock.ToDto(eq(menu))).thenReturn(menuResponseDTO);

        // Llamar al método que se está probando con datos válidos
        MenuResponseDTO result = menuService.create(menu);

        // Verificar el comportamiento esperado
        verify(menuRepositoryMock, times(1)).save(eq(menu));
        verify(menuMapperMock, times(1)).ToDto(eq(menu));
        // Verificar que el resultado no sea nulo
        // Por ejemplo:
        // assertNotNull(result);
    }

    @Test
    public void testUpdateMenuIfExists() throws Exception {
        // Crear un objeto Menu ficticio para la prueba
        Menu existingMenu = new Menu();
        existingMenu.setId(1L); // ID existente
        existingMenu.setPrice(10000);
        existingMenu.setRestaurant("Restaurante A");

        // Configurar el comportamiento simulado para menuRepository.findById
        when(menuRepositoryMock.findById(anyLong())).thenReturn(Optional.of(existingMenu));

        // Configurar el comportamiento simulado para menuRepository.save
        when(menuRepositoryMock.save(any(Menu.class))).thenReturn(existingMenu);

        // Configurar el comportamiento simulado para menuMapper.ToDto
        MenuResponseDTO menuResponseDTO = new MenuResponseDTO();
        when(menuMapperMock.ToDto(eq(existingMenu))).thenReturn(menuResponseDTO);

        // Llamar al método que se está probando para actualizar un menú existente
        MenuResponseDTO result = menuService.update(1L, existingMenu);

        // Verificar el comportamiento esperado
        verify(menuRepositoryMock, times(1)).findById(eq(1L));
        verify(menuRepositoryMock, times(1)).save(eq(existingMenu));
        verify(menuMapperMock, times(1)).ToDto(eq(existingMenu));
        // Verificar que el resultado no sea nulo
        // Por ejemplo:
        // assertNotNull(result);
    }


    @Test
    public void testUpdateStateMenuIfNotExists() {
        // Configurar el comportamiento simulado para menuRepository.findById
        when(menuRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        // Crear un objeto Menu ficticio para la prueba
        Menu menuToUpdateState = new Menu();
        menuToUpdateState.setId(1L); // ID no existente

        // Llamar al método que se está probando con un menú que no existe
        // Debería lanzar una excepción
        assertThrows(Exception.class, () -> {
            menuService.updateState(1L, menuToUpdateState);
        });

        // Verificar que se lanzó la excepción esperada
        // Por ejemplo:
        // assertThrows(Exception.class, () -> menuService.updateState(1L, menuToUpdateState));
    }

    @Test
    public void testDeleteMenuIfExists() throws Exception {
        // Configurar el comportamiento simulado para menuRepository.findById
        when(menuRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new Menu()));

        // Llamar al método que se está probando para eliminar un menú existente
        boolean result = menuService.delete(1L);

        // Verificar el comportamiento esperado
        verify(menuRepositoryMock, times(1)).findById(eq(1L));
        verify(menuRepositoryMock, times(1)).deleteById(eq(1L));
        assertTrue(result); // Verificar que se devuelve true
    }


    @Test
    public void testSearchByCategoryAndRestaurant() throws Exception {
        // Configurar el comportamiento simulado para menuRepository.findByRestaurantAndAndCategory
        List<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu());
        Page<Menu> menuPage = new PageImpl<>(menuList);
        when(menuRepositoryMock.findByRestaurantAndAndCategory(anyString(), anyString(), any(Pageable.class)))
                .thenReturn(menuPage);

        // Configurar el comportamiento simulado para menuMapper.ToDto
        MenuResponseDTO menuResponseDTO = new MenuResponseDTO();
        when(menuMapperMock.ToDto(any(Menu.class))).thenReturn(menuResponseDTO);

        // Llamar al método que se está probando para buscar menús por categoría y restaurante
        Page<MenuResponseDTO> result = menuService.searchByCategoryAndRestaurant("Categoria", "Restaurante", 10);

        // Verificar el comportamiento esperado
        verify(menuRepositoryMock, times(1)).findByRestaurantAndAndCategory(eq("Restaurante"), eq("Categoria"), any(Pageable.class));
        verify(menuMapperMock, times(1)).ToDto(any(Menu.class));
        // Verificar que el resultado no sea nulo
        // Por ejemplo:
        // assertNotNull(result);
    }
}