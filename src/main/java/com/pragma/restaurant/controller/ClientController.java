package com.pragma.restaurant.controller;

import com.pragma.restaurant.dto.client.ClientDTO;
import com.pragma.restaurant.dto.client.ClientErrorDTO;
import com.pragma.restaurant.dto.client.ClientResponseDTO;


import com.pragma.restaurant.entity.Client;


import com.pragma.restaurant.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/")
    public ResponseEntity<List<ClientResponseDTO>> getAll() {
        try {
            return ResponseEntity
                    .ok()
                    .body(clientService.searchAll());
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<ClientDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok()
                    .body(clientService.searchById(id));
        } catch (Exception e) {
            ClientErrorDTO resError = new ClientErrorDTO();
            resError.setError(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(resError);
        }
    }

    @PostMapping("/")
    ResponseEntity<ClientDTO> create(@RequestBody Client data) {
        try {
            return ResponseEntity
                    .ok()
                    .body(clientService.create(data));
        } catch (Exception e) {
            ClientErrorDTO resError = new ClientErrorDTO();
            resError.setError(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(resError);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody Client data) {
        try {
            return ResponseEntity
                    .ok()
                    .body(clientService.update(id, data));
        } catch (Exception e) {
            ClientErrorDTO resError = new ClientErrorDTO();
            resError.setError(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(resError);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> delete(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok()
                    .body(clientService.delete(id));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(false);
        }
    }
}
