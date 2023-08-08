package com.pragma.restaurant.service;


import com.pragma.restaurant.dto.client.ClientResponseDTO;
import com.pragma.restaurant.dto.employee.EmployeeResponseDTO;
import com.pragma.restaurant.entity.Client;
import com.pragma.restaurant.entity.Employee;
import com.pragma.restaurant.mapper.EmployeeMapper;
;
import com.pragma.restaurant.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService implements BaseService<EmployeeResponseDTO, Employee> {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper  employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }


    public EmployeeResponseDTO create(Employee employee) throws Exception{
        try {

            return employeeMapper.ToDto(employeeRepository.save(employee));
        }catch (Exception e){

            throw new Exception(e.getMessage());
        }
    }


    public List<EmployeeResponseDTO> searchAll()throws Exception{
        try {

            return employeeMapper.ToDtoList(employeeRepository.findAll());
        }catch (Exception e){

            throw new Exception(e.getMessage());
        }
    }
}
