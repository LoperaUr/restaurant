package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.menu.MenuDTO;
import com.pragma.restaurant.entity.Menu;
import com.pragma.restaurant.mapper.MenuMapper;
import com.pragma.restaurant.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService implements BaseService<MenuDTO, Menu> {

    private final MenuRepository menuRepository;

    private  final MenuMapper menuMapper;

    public MenuService(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }


    @Override
    public List<MenuDTO> searchAll() throws Exception {
        return null;
    }

    @Override
    public MenuDTO searchById(Long id) throws Exception {
        return null;
    }

    @Override
    public MenuDTO create(Menu data) throws Exception {
        return null;
    }

    @Override
    public MenuDTO update(Long id, Menu data) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }
}
