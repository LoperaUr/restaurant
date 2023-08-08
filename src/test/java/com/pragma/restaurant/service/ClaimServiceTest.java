package com.pragma.restaurant.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pragma.restaurant.dto.claim.ClaimResponseDTO;
import com.pragma.restaurant.entity.Claim;
import com.pragma.restaurant.entity.Order;
import com.pragma.restaurant.mapper.ClaimMapper;
import com.pragma.restaurant.repository.ClaimRepository;
import com.pragma.restaurant.repository.OrderRepository;
import com.pragma.restaurant.util.StateClaim;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClaimServiceTest {

    private ClaimService claimService;
    private ClaimRepository claimRepositoryMock;
    private OrderRepository orderRepositoryMock;
    private ClaimMapper claimMapperMock;

    @BeforeEach
    public void setUp() {
        claimRepositoryMock = mock(ClaimRepository.class);
        orderRepositoryMock = mock(OrderRepository.class);
        claimMapperMock = mock(ClaimMapper.class);

        claimService = new ClaimService(
                claimRepositoryMock,
                orderRepositoryMock,
                claimMapperMock
        );
    }

    @Test
    public void testCreateClaim() throws Exception {
        // Configurar el comportamiento simulado para orderRepository.existsById
        when(orderRepositoryMock.existsById(anyLong())).thenReturn(true);

        // Configurar el comportamiento simulado para orderRepository.findById
        when(orderRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new Order()));

        // Configurar el comportamiento simulado para claimRepository.save
        when(claimRepositoryMock.save(any(Claim.class))).thenReturn(new Claim());

        // Configurar el comportamiento simulado para claimMapper.toDto
        ClaimResponseDTO claimResponseDTO = new ClaimResponseDTO();
        when(claimMapperMock.toDto(any(Claim.class))).thenReturn(claimResponseDTO);

        // Llamar al método que se está probando para crear un reclamo
        ClaimResponseDTO result = claimService.createClaim(new Claim());

        // Verificar el comportamiento esperado
        verify(orderRepositoryMock, times(2)).existsById(anyLong());
        verify(orderRepositoryMock, times(1)).findById(anyLong());
        verify(claimRepositoryMock, times(1)).save(any(Claim.class));
        verify(claimMapperMock, times(1)).toDto(any(Claim.class));
        // Verificar que el resultado no sea nulo
        // Por ejemplo:
        // assertNotNull(result);
    }

    @Test
    public void testGetClaimsPending() throws Exception {
        // Configurar el comportamiento simulado para claimRepository.findAllByClaimState
        List<Claim> claimList = new ArrayList<>();
        claimList.add(new Claim());
        when(claimRepositoryMock.findAllByClaimState(any(StateClaim.class))).thenReturn(claimList);

        // Configurar el comportamiento simulado para claimMapper.toDtoList
        ClaimResponseDTO claimResponseDTO = new ClaimResponseDTO();
        when(claimMapperMock.toDtoList(anyList())).thenReturn(List.of(claimResponseDTO));

        // Llamar al método que se está probando para obtener reclamos pendientes
        List<ClaimResponseDTO> result = claimService.getClaimsPending();

        // Verificar el comportamiento esperado
        verify(claimRepositoryMock, times(1)).findAllByClaimState(any(StateClaim.class));
        verify(claimMapperMock, times(1)).toDtoList(anyList());
        // Verificar que el resultado no sea nulo
        // Por ejemplo:
        // assertNotNull(result);
    }

    @Test
    public void testUpdateStateClaimIfExists() throws Exception {
        // Configurar el comportamiento simulado para claimRepository.existsById
        when(claimRepositoryMock.existsById(anyLong())).thenReturn(true);

        // Configurar el comportamiento simulado para claimRepository.findById
        when(claimRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new Claim()));

        // Configurar el comportamiento simulado para claimRepository.save
        when(claimRepositoryMock.save(any(Claim.class))).thenReturn(new Claim());

        // Configurar el comportamiento simulado para claimMapper.toDto
        ClaimResponseDTO claimResponseDTO = new ClaimResponseDTO();
        when(claimMapperMock.toDto(any(Claim.class))).thenReturn(claimResponseDTO);

        // Llamar al método que se está probando para actualizar el estado de un reclamo existente
        ClaimResponseDTO result = claimService.updateStateClaim(1L, StateClaim.ACCEPTED);

        // Verificar el comportamiento esperado
        verify(claimRepositoryMock, times(1)).existsById(eq(1L));
        verify(claimRepositoryMock, times(1)).findById(eq(1L));
        verify(claimRepositoryMock, times(1)).save(any(Claim.class));
        verify(claimMapperMock, times(1)).toDto(any(Claim.class));
        // Verificar que el resultado no sea nulo
        // Por ejemplo:
        // assertNotNull(result);
    }

    @Test
    public void testUpdateStateClaimIfNotExists() {
        // Configurar el comportamiento simulado para claimRepository.existsById
        when(claimRepositoryMock.existsById(anyLong())).thenReturn(false);

        // Llamar al método que se está probando con un reclamo que no existe
        // Debería lanzar una excepción
        assertThrows(Exception.class, () -> {
            claimService.updateStateClaim(1L, StateClaim.ACCEPTED);
        });

        // Verificar que se lanzó la excepción esperada
        // Por ejemplo:
        // assertThrows(Exception.class, () -> claimService.updateStateClaim(1L, StateClaim.APPROVED));
    }

    @Test
    public void testDeleteClaim() throws Exception {
        // Llamar al método que se está probando para eliminar un reclamo
        boolean result = claimService.delete(1L);

        // Verificar el comportamiento esperado
        verify(claimRepositoryMock, times(1)).deleteById(eq(1L));
        assertTrue(result); // Verificar que se devuelve true
    }

    // Agregar más métodos de prueba para otros escenarios en update, delete, etc.
    // ...
}
