package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.claim.ClaimDTO;
import com.pragma.restaurant.entity.Claim;

import java.util.List;

public class ClaimService implements BaseService<ClaimDTO, Claim> {

    @Override
    public List<ClaimDTO> searchAll() throws Exception {
        return null;
    }

    @Override
    public ClaimDTO searchById(Long id) throws Exception {
        return null;
    }

    @Override
    public ClaimDTO create(Claim data) throws Exception {
        return null;
    }

    @Override
    public ClaimDTO update(Long id, Claim data) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }
}
