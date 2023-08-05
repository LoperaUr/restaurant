package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.claim.ClaimResponseDTO;
import com.pragma.restaurant.entity.Claim;
import com.pragma.restaurant.mapper.ClaimMapper;
import com.pragma.restaurant.repository.ClaimRepository;
import com.pragma.restaurant.repository.OrderRepository;
import org.springframework.stereotype.Service;

import static com.pragma.restaurant.validation.ClaimValidation.validateDescription;

@Service

public class ClaimService implements BaseService<ClaimResponseDTO, Claim> {

    private final ClaimRepository claimRepository;

    private final OrderRepository orderRepository;

    private final ClaimMapper claimMapper;

    public ClaimService(ClaimRepository claimRepository, ClaimMapper claimMapper, ClaimRepository claimRepository1, OrderRepository orderRepository, ClaimMapper claimMapper1) {
        this.claimRepository = claimRepository1;
        this.orderRepository = orderRepository;
        this.claimMapper = claimMapper1;
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

    public boolean delete(Long id) throws Exception {
        try {
            claimRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
