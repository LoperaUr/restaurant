package com.pragma.restaurant.mapper;


import com.pragma.restaurant.dto.employee.EmployeeResponseDTO;
import com.pragma.restaurant.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {





    // Método que convierte una lista de objetos Employee a una lista de objetos ResponseEmployeeDTO
    List<EmployeeResponseDTO> toEmployeesDTO(List<Employee> employees);

    EmployeeResponseDTO toEmployeeDTO(Employee employee);
}
