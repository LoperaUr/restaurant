package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.menu.MenuResponseDTO;
import com.pragma.restaurant.entity.Menu;
import com.pragma.restaurant.mapper.MenuMapper;
import com.pragma.restaurant.repository.MenuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.pragma.restaurant.validation.MenuValidation.validatePrice;
import static com.pragma.restaurant.validation.MenuValidation.validateRestaurant;

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
        try {
            if (!data.getRol().equals('A')) {
                throw new Exception("No tiene permisos para crear un menu");
            }
            if (!validatePrice(data.getPrice())) {
                throw new Exception("El precio debe ser mayor a 0");
            }
            if (!validateRestaurant(data.getRestaurant())) {
                throw new Exception("No existe el restaurante");
            }
            return menuMapper.ToDto(menuRepository.save(data));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public MenuResponseDTO update(Long id, Menu data) throws Exception {
        try {
            Optional<Menu> menuOptional = menuRepository.findById(id);
            if (menuOptional.isPresent()) {
                Menu menuExist = menuOptional.get();
                menuExist.setPrice(data.getPrice());
                menuExist.setRestaurant(data.getRestaurant());
                menuExist.setDescription(data.getDescription());
                return menuMapper.ToDto(menuRepository.save(menuExist));
            } else {
                throw new Exception("No existe el menu");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public MenuResponseDTO updateState(Long id, Menu data) throws Exception {
        try {
            if (!data.getRol().equals('A')) {
                throw new Exception("No tiene permisos para crear un menu");
            }
            Optional<Menu> menuOptional = menuRepository.findById(id);
            if (menuOptional.isPresent()) {
                Menu menuExist = menuOptional.get();
                menuExist.setState(data.getState());
                return menuMapper.ToDto(menuRepository.save(menuExist));
            }
            throw new Exception("Menu no existe");
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

    public Page<MenuResponseDTO> searchByCategoryAndRestaurant(String category, String restaurant, int size) throws Exception {
        try {
            Pageable pageable = PageRequest.of(0, size);
            Page<Menu> menus = menuRepository.findByRestaurantAndAndCategory(restaurant, category, pageable);
            return menus.map(menuMapper::ToDto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
