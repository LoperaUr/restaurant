package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.menu.MenuResponseDTO;
import com.pragma.restaurant.entity.Menu;
import com.pragma.restaurant.mapper.MenuMapper;
import com.pragma.restaurant.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService implements BaseService<MenuResponseDTO, Menu> {

    private final MenuRepository menuRepository;

    private final MenuMapper menuMapper;

    public MenuService(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }


    @Override
    public List<MenuResponseDTO> searchAll() throws Exception {
        try {
            return menuMapper.toDtoList(menuRepository.findAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public MenuResponseDTO searchById(Long id) throws Exception {
        try {
            return menuMapper.ToDto(menuRepository.findById(id).get());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public MenuResponseDTO create(Menu data) throws Exception {
        return menuMapper.ToDto(menuRepository.save(data));
    }

    @Override
    public MenuResponseDTO update(Long id, Menu data) throws Exception {
        try {
            Optional<Menu> menuOptional = menuRepository.findById(id);
            if (menuOptional.isPresent()) {
                Menu menuExist = menuOptional.get();
                menuExist.setName(data.getName());
                menuExist.setPrice(data.getPrice());
                return menuMapper.ToDto(menuRepository.save(menuExist));
            } else {
                throw new Exception("No existe el menu");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try {
            Optional<Menu> menuOptional = menuRepository.findById(id);
            if (menuOptional.isPresent()) {
                menuRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No existe el menu");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
