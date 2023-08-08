package com.pragma.restaurant.controller;

import com.pragma.restaurant.dto.menu.MenuDTO;
import com.pragma.restaurant.dto.menu.MenuErrorDTO;
import com.pragma.restaurant.dto.menu.MenuResponseDTO;
import com.pragma.restaurant.entity.Menu;
import com.pragma.restaurant.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/menu")
@Api(value = "Menu Management System", description = "Operations related to menus")

public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all menus")
    public ResponseEntity<List<MenuResponseDTO>> getAll() {
        try {
            return ResponseEntity
                    .ok()
                    .body(menuService.searchAll());
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }



    @PostMapping("/")
    @ApiOperation(value = "Create a menu")
    ResponseEntity<MenuDTO> create(@RequestBody Menu data) {
        try {
            return ResponseEntity
                    .ok()
                    .body(menuService.create(data));
        } catch (Exception e) {
            MenuErrorDTO resError = new MenuErrorDTO();
            resError.setError(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(resError);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a menu by ID")
    ResponseEntity<MenuDTO> update(@PathVariable Long id, @RequestBody Menu data) {
        try {
            return ResponseEntity
                    .ok()
                    .body(menuService.update(id, data));
        } catch (Exception e) {
            MenuErrorDTO resError = new MenuErrorDTO();
            resError.setError(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(resError);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a menu by ID")
    ResponseEntity<Boolean> delete(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok()
                    .body(menuService.delete(id));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(false);
        }
    }
}

