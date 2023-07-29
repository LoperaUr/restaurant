package com.pragma.restaurant.controller;

import com.pragma.restaurant.dto.claim.ClaimDTO;
import com.pragma.restaurant.dto.claim.ClaimErrorDTO;
import com.pragma.restaurant.dto.claim.ClaimResponseDTO;
import com.pragma.restaurant.entity.Claim;
import com.pragma.restaurant.service.ClaimService;
import com.pragma.restaurant.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/claim")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }


    @GetMapping("/")
    public ResponseEntity<List<ClaimResponseDTO>> getAll() {
        try {
            return ResponseEntity
                    .ok()
                    .body(ClaimService.searchAll());
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<ClaimDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok()
                    .body(ClaimService.searchById(id));
        } catch (Exception e) {
            ClaimErrorDTO resError = new ClaimErrorDTO();
            resError.setError(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(resError);
        }
    }

    @PostMapping("/")
    ResponseEntity<ClaimDTO> create(@RequestBody Claim data) {
        try {
            return ResponseEntity
                    .ok()
                    .body(ClaimService.create(data));
        } catch (Exception e) {
            ClaimErrorDTO resError = new ClaimErrorDTO();
            resError.setError(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(resError);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<ClaimDTO> update(@PathVariable Long id, @RequestBody Claim data) {
        try {
            return ResponseEntity
                    .ok()
                    .body(ClaimService.update(id, data));
        } catch (Exception e) {
            ClaimErrorDTO resError = new ClaimErrorDTO();
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
                    .body(claimService.delete(id));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(false);
        }
    }
}
