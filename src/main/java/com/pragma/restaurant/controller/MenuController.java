package com.pragma.restaurant.controller;

import com.pragma.restaurant.dto.menu.MenuResponseDTO;
import com.pragma.restaurant.entity.Menu;
import com.pragma.restaurant.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/")
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

    @GetMapping("/{id}")
    ResponseEntity<MenuResponseDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok()
                    .body(menuService.searchById(id));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @PostMapping("/")
    ResponseEntity<MenuResponseDTO> create(@RequestBody Menu data) {
        try {
            return ResponseEntity
                    .ok()
                    .body(menuService.create(data));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<MenuResponseDTO> update(@PathVariable Long id, @RequestBody Menu data) {
        try {
            return ResponseEntity
                    .ok()
                    .body(menuService.update(id, data));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> delete(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok()
                    .body(menuService.delete(id));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }
}

