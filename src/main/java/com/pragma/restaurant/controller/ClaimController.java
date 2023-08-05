package com.pragma.restaurant.controller;

import com.pragma.restaurant.dto.claim.ClaimDTO;
import com.pragma.restaurant.dto.claim.ClaimErrorDTO;
import com.pragma.restaurant.entity.Claim;
import com.pragma.restaurant.service.ClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/claim")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping("/")
    ResponseEntity<ClaimDTO> create(@RequestBody Claim data) {
        try {
            return ResponseEntity
                    .ok()
                    .body(claimService.createClaim(data));
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
