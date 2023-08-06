package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.claim.ClaimResponseDTO;
import com.pragma.restaurant.entity.Claim;
import com.pragma.restaurant.mapper.ClaimMapper;
import com.pragma.restaurant.repository.ClaimRepository;
import com.pragma.restaurant.repository.OrderRepository;
import com.pragma.restaurant.util.StateClaim;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pragma.restaurant.validation.ClaimValidation.validateDescription;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;

    private final OrderRepository orderRepository;

    private final ClaimMapper claimMapper;

    public ClaimService(ClaimRepository claimRepository, OrderRepository orderRepository, ClaimMapper claimMapper) {
        this.claimRepository = claimRepository;
        this.orderRepository = orderRepository;
        this.claimMapper = claimMapper;
    }


    public ClaimResponseDTO createClaim(Claim data) throws Exception {
        try {
            if (!orderRepository.existsById(data.getOrderId().getId())) {
                throw new Exception("El id del pedido no existe");
            }
            if (validateDescription(data.getDescription())) {
                throw new Exception("La descripción no puede estar vacia");
            }
            data.setRestaurant(orderRepository.findById(data.getOrderId().getId()).get().getRestaurant());
            return claimMapper.toDto(claimRepository.save(data));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<ClaimResponseDTO> getClaimsPending() throws Exception {
        try {
            return claimMapper.toDtoList(claimRepository.findAllByClaimState(StateClaim.PENDING));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ClaimResponseDTO updateStateClaim(Long id, StateClaim state) throws Exception {
        try {
            if (!claimRepository.existsById(id)) {
                throw new Exception("El id del reclamo no existe");
            }
            Claim claim = claimRepository.findById(id).get();
            claim.setClaimState(state);
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
