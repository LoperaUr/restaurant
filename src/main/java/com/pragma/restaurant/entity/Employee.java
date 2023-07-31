package com.pragma.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "employee")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "")
    @JsonManagedReference
    @JsonIgnore
    private List<Order> assignedEmployee;

    public Employee() {
    }

    public Employee(Long id, String name, List<Order> assignedEmployee) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(List<Order> assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }
}
