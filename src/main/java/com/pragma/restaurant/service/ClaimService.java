package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.claim.ClaimResponseDTO;
import com.pragma.restaurant.entity.Claim;
import com.pragma.restaurant.mapper.ClaimMapper;
import com.pragma.restaurant.repository.ClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ClaimService implements BaseService<ClaimResponseDTO, Claim> {

    private final ClaimRepository claimRepository;

    private final ClaimMapper claimMapper;

    public ClaimService(ClaimRepository claimRepository, ClaimMapper claimMapper) {
        this.claimRepository = claimRepository;
        this.claimMapper = claimMapper;
    }

    @Override
    public  List<ClaimResponseDTO> searchAll() throws Exception {
        return null;
    }

    @Override
    public ClaimResponseDTO searchById(Long id) throws Exception {
        return null;
    }

    @Override
    public ClaimResponseDTO create(Claim data) throws Exception {
        return null;
    }

    @Override
    public ClaimResponseDTO update(Long id, Claim data) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }
}
