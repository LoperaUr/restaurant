package com.pragma.restaurant.repository;

import com.pragma.restaurant.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>   {

    Employee findByNameEmployeeId(String nameEmployeeId);


}
