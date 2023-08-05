package com.pragma.restaurant.controller;



import com.pragma.restaurant.dto.employee.EmployeeDTO;
import com.pragma.restaurant.dto.employee.EmployeeErrorDTO;
import com.pragma.restaurant.dto.employee.EmployeeResponseDTO;
import com.pragma.restaurant.entity.Employee;
import com.pragma.restaurant.service.EmployeeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeResponseDTO>> getAll() {
        try {
            return ResponseEntity
                    .ok()
                    .body(employeeService.searchAll());
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @GetMapping("/")
   ResponseEntity<EmployeeDTO> create(@RequestBody Employee employee){
        try {
            return ResponseEntity
                    .ok()
                    .body(employeeService.create(employee));
        } catch (Exception e) {
            EmployeeErrorDTO resError = new EmployeeErrorDTO();
            resError.setError(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(resError);
        }
    }


}