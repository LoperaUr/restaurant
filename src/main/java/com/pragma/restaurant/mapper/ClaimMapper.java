package com.pragma.restaurant.mapper;

import com.pragma.restaurant.dto.claim.ClaimResponseDTO;
import com.pragma.restaurant.entity.Claim;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ClaimMapper {
    ClaimResponseDTO ToDto(Claim claim);

    List<ClaimResponseDTO> toDtoList(List<Claim> claim);

    ClaimResponseDTO toDto(Claim save);
}
