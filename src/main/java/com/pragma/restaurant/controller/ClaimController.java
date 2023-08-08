package com.pragma.restaurant.controller;

import com.pragma.restaurant.dto.claim.ClaimDTO;
import com.pragma.restaurant.dto.claim.ClaimErrorDTO;
import com.pragma.restaurant.dto.claim.ClaimResponseDTO;
import com.pragma.restaurant.entity.Claim;
import com.pragma.restaurant.service.ClaimService;
import com.pragma.restaurant.util.StateClaim;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/claim")
@Api(value = "Claim Management System", description = "Operations related to claims")

public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping("/")
    @ApiOperation(value = "Create a claim")
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

    @GetMapping("/")
    @ApiOperation(value = "Get claims with pending status")
    ResponseEntity<List<ClaimResponseDTO>> searchStatusPending() {
        try {
            return ResponseEntity
                    .ok()
                    .body(claimService.getClaimsPending());
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update claim state by ID")
    ResponseEntity<ClaimResponseDTO> updateState(
            @PathVariable Long id,
            @RequestParam StateClaim state
    ) {
        try {
            return ResponseEntity
                    .ok()
                    .body(claimService.updateStateClaim(id, state));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a claim by ID")
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
