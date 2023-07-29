package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.client.ClientDTO;
import com.pragma.restaurant.dto.client.ClientResponseDTO;
import com.pragma.restaurant.dto.menu.MenuResponseDTO;
import com.pragma.restaurant.entity.Client;
import com.pragma.restaurant.entity.Menu;
import com.pragma.restaurant.mapper.ClientMapper;
import com.pragma.restaurant.mapper.MenuMapper;
import com.pragma.restaurant.repository.ClientRepository;
import com.pragma.restaurant.repository.MenuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.pragma.restaurant.validation.MenuValidation.validatePrice;
import static com.pragma.restaurant.validation.MenuValidation.validateRestaurant;

public class ClientService implements BaseService<ClientDTO,Client> {

    private final ClientRepository clientRepository;


    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientResponseDTO> searchAll() throws Exception {
        try {
            return clientMapper.toDtoList(clientRepository.findAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ClientResponseDTO searchById(Long id) throws Exception {
        try {
            return clientMapper.ToDto(clientRepository.findById(id).get());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ClientResponseDTO create(Client data) throws Exception {
        try {

            return clientMapper.ToDto(clientRepository.save(data));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
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



    @Override
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
