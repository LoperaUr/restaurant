package com.pragma.restaurant.mapper;

import com.pragma.restaurant.dto.employee.EmployeeResponseDTO;
import com.pragma.restaurant.entity.Employee;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")

public interface EmployeeMapper {
    EmployeeResponseDTO ToDto(Employee employeeId);

    List<EmployeeResponseDTO> ToDtoList(List<Employee> employeeId);
}
