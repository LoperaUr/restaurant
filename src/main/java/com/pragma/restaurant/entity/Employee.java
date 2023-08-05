package com.pragma.restaurant.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="employee")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "name_employee")
    private String nameEmployee;
    private String password;
    @OneToMany(mappedBy = "employeeId")
    @JsonManagedReference
    @JsonIgnore
    private List<Order> orders;

    public Employee() {
    }

    public Employee(Long employeeId, String nameEmployee, String password, List<Order> orders) {
        this.employeeId = employeeId;
        this.nameEmployee = nameEmployee;
        this.password = password;
        this.orders = orders;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
