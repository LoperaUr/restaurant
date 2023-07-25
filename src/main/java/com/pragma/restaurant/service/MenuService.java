package com.pragma.restaurant.service;

import com.pragma.restaurant.mapper.MenuMapper;
import com.pragma.restaurant.repository.MenuRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    private  final MenuMapper menuMapper;

    public MenuService(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }
}
