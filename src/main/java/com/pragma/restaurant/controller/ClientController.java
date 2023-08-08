package com.pragma.restaurant.controller;

import com.pragma.restaurant.dto.client.ClientDTO;
import com.pragma.restaurant.dto.client.ClientErrorDTO;
import com.pragma.restaurant.dto.client.ClientResponseDTO;


import com.pragma.restaurant.entity.Client;


import com.pragma.restaurant.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/client")
@Api(value = "Client Management System", description = "Operations related to clients")

public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/")
    @ApiOperation(value = "Get all clients")

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



    @PostMapping("/")
    @ApiOperation(value = "Create a client")
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
    @ApiOperation(value = "Update a client by ID")
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
    @ApiOperation(value = "Delete a client by ID")
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
