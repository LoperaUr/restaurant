package com.pragma.restaurant.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pragma.restaurant.dto.client.ClientResponseDTO;
import com.pragma.restaurant.entity.Client;
import com.pragma.restaurant.mapper.ClientMapper;
import com.pragma.restaurant.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientServiceTest {

    private ClientService clientService;
    private ClientRepository clientRepositoryMock;
    private ClientMapper clientMapperMock;

    @BeforeEach
    public void setUp() {
        clientRepositoryMock = mock(ClientRepository.class);
        clientMapperMock = mock(ClientMapper.class);

        clientService = new ClientService(
                clientRepositoryMock,
                clientMapperMock
        );
    }

    @Test
    public void testSearchAllClients() throws Exception {
        // Configurar el comportamiento simulado para clientRepository.findAll
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client());
        when(clientRepositoryMock.findAll()).thenReturn(clientList);

        // Configurar el comportamiento simulado para clientMapper.toDtoList
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        when(clientMapperMock.toDtoList(anyList())).thenReturn(List.of(clientResponseDTO));

        // Llamar al método que se está probando para buscar todos los clientes
        List<ClientResponseDTO> result = clientService.searchAll();

        // Verificar el comportamiento esperado
        verify(clientRepositoryMock, times(1)).findAll();
        verify(clientMapperMock, times(1)).toDtoList(anyList());
        // Verificar que el resultado no sea nulo
        // Por ejemplo:
        // assertNotNull(result);
    }

    @Test
    public void testCreateClient() throws Exception {
        // Configurar el comportamiento simulado para clientRepository.save
        when(clientRepositoryMock.save(any(Client.class))).thenReturn(new Client());

        // Configurar el comportamiento simulado para clientMapper.ToDto
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        when(clientMapperMock.ToDto(any(Client.class))).thenReturn(clientResponseDTO);

        // Llamar al método que se está probando para crear un cliente
        ClientResponseDTO result = clientService.create(new Client());

        // Verificar el comportamiento esperado
        verify(clientRepositoryMock, times(1)).save(any(Client.class));
        verify(clientMapperMock, times(1)).ToDto(any(Client.class));
        // Verificar que el resultado no sea nulo
        // Por ejemplo:
        // assertNotNull(result);
    }

    @Test
    public void testUpdateClientIfExists() throws Exception {
        // Configurar el comportamiento simulado para clientRepository.findById
        when(clientRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new Client()));

        // Configurar el comportamiento simulado para clientMapper.ToDto
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        when(clientMapperMock.ToDto(any(Client.class))).thenReturn(clientResponseDTO);

        // Llamar al método que se está probando para actualizar un cliente existente
        ClientResponseDTO result = clientService.update(1L, new Client());

        // Verificar el comportamiento esperado
        verify(clientRepositoryMock, times(1)).findById(eq(1L));
        verify(clientRepositoryMock, times(1)).save(any(Client.class));
        verify(clientMapperMock, times(1)).ToDto(any(Client.class));
        // Verificar que el resultado no sea nulo
        // Por ejemplo:
        // assertNotNull(result);
    }

    @Test
    public void testUpdateClientIfNotExists() {
        // Configurar el comportamiento simulado para clientRepository.findById
        when(clientRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        // Llamar al método que se está probando con un cliente que no existe
        // Debería lanzar una excepción
        assertThrows(Exception.class, () -> {
            clientService.update(1L, new Client());
        });

        // Verificar que se lanzó la excepción esperada
        // Por ejemplo:
        // assertThrows(Exception.class, () -> clientService.update(1L, new Client()));
    }

    @Test
    public void testDeleteClientIfExists() throws Exception {
        // Configurar el comportamiento simulado para clientRepository.findById
        when(clientRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new Client()));

        // Llamar al método que se está probando para eliminar un cliente existente
        boolean result = clientService.delete(1L);

        // Verificar el comportamiento esperado
        verify(clientRepositoryMock, times(1)).findById(eq(1L));
        verify(clientRepositoryMock, times(1)).deleteById(eq(1L));
        assertTrue(result); // Verificar que se devuelve true
    }

    @Test
    public void testDeleteClientIfNotExists() {
        // Configurar el comportamiento simulado para clientRepository.findById
        when(clientRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        // Llamar al método que se está probando con un cliente que no existe
        // Debería lanzar una excepción
        assertThrows(Exception.class, () -> {
            clientService.delete(1L);
        });

        // Verificar que se lanzó la excepción esperada
        // Por ejemplo:
        // assertThrows(Exception.class, () -> clientService.delete(1L));
    }

    // Agregar más métodos de prueba para otros escenarios en update, delete, etc.
    // ...
}