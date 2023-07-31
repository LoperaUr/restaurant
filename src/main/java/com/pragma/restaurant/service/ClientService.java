package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.client.ClientDTO;
import com.pragma.restaurant.dto.client.ClientResponseDTO;

import com.pragma.restaurant.entity.Client;

import com.pragma.restaurant.mapper.ClientMapper;

import com.pragma.restaurant.repository.ClientRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClientService implements BaseService<ClientResponseDTO, Client> {

    private final ClientRepository clientRepository;


    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }


    public List<ClientResponseDTO> searchAll() throws Exception {
        try {
            return clientMapper.toDtoList(clientRepository.findAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public ClientResponseDTO create(Client data) throws Exception {
        try {

            return clientMapper.ToDto(clientRepository.save(data));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public ClientResponseDTO update(Long id, Client data) throws Exception {
        try {
            Optional<Client> clientOptional = clientRepository.findById(id);
            if (clientOptional.isPresent()) {
                Client clientExist = clientOptional.get();
                clientExist.setName(data.getName());

                return clientMapper.ToDto(clientRepository.save(clientExist));
            } else {
                throw new Exception("No existe el cliente");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public boolean delete(Long id) throws Exception {
        try {
            Optional<Client> clientOptional = clientRepository.findById(id);
            if (clientOptional.isPresent()) {
                clientRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No existe el cliente");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
