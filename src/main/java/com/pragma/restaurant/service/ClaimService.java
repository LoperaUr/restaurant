package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.claim.ClaimResponseDTO;
import com.pragma.restaurant.entity.Claim;
import com.pragma.restaurant.mapper.ClaimMapper;
import com.pragma.restaurant.repository.ClaimRepository;
import org.springframework.stereotype.Service;

@Service

public class ClaimService implements BaseService<ClaimResponseDTO, Claim> {

    private final ClaimRepository claimRepository;

    private final ClaimMapper claimMapper;
    public ClaimService(ClaimRepository claimRepository, ClaimMapper claimMapper, ClaimRepository claimRepository1, ClaimMapper claimMapper1) {
        this.claimRepository = claimRepository1;
        this.claimMapper = claimMapper1;
    }



    public ClaimResponseDTO create(Claim claim) throws Exception {


 try {
            return claimMapper.toDto(claimRepository.save(claim));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean delete(Long id) throws Exception {
        try {
            claimRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
