package com.pragma.restaurant.service;


import com.pragma.restaurant.dto.employee.EmployeeResponseDTO;
import com.pragma.restaurant.entity.Employee;
import com.pragma.restaurant.mapper.EmployeeMapper;
import com.pragma.restaurant.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeService(employeeRepository, employeeMapper);
    }

    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = new Employee();
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(employeeMapper.ToDto(any(Employee.class))).thenReturn(responseDTO);

        EmployeeResponseDTO createdEmployee = employeeService.create(employee);

        assertEquals(responseDTO, createdEmployee);
    }

    @Test
    public void testSearchAllEmployees() throws Exception {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        EmployeeResponseDTO responseDTO1 = new EmployeeResponseDTO();
        EmployeeResponseDTO responseDTO2 = new EmployeeResponseDTO();
        List<EmployeeResponseDTO> responseDTOList = new ArrayList<>();
        responseDTOList.add(responseDTO1);
        responseDTOList.add(responseDTO2);

        when(employeeRepository.findAll()).thenReturn(employeeList);
        when(employeeMapper.ToDtoList(employeeList)).thenReturn(responseDTOList);

        List<EmployeeResponseDTO> employees = employeeService.searchAll();

        assertEquals(2, employees.size());
        assertEquals(responseDTO1, employees.get(0));
        assertEquals(responseDTO2, employees.get(1));
    }
}