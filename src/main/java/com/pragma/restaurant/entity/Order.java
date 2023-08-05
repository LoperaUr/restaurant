package com.pragma.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pragma.restaurant.util.StateOrder;
import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Character rolRequest;
    private Character rolAp;

    @Column(name = "restaurant", nullable = false)
    private String restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private Client userOrder;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "employeeId")
    private Employee employeeId = null;

    @Enumerated(EnumType.STRING)
    private StateOrder orderState = StateOrder.PENDING;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    @JsonManagedReference
    private List<OrderDetails> menuList;


    public Order() {
    }

    public Order(Long id, Character rolRequest, Character rolAp, String restaurant, Client userOrder, Employee employeeId, StateOrder orderState, List<OrderDetails> menuList) {
        this.id = id;
        this.rolRequest = rolRequest;
        this.rolAp = rolAp;
        this.restaurant = restaurant;
        this.userOrder = userOrder;
        this.employeeId = employeeId;
        this.orderState = orderState;
        this.menuList = menuList;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getRolRequest() {
        return rolRequest;
    }

    public void setRolRequest(Character rolRequest) {
        this.rolRequest = rolRequest;
    }

    public Character getRolAp() {
        return rolAp;
    }

    public void setRolAp(Character rolAp) {
        this.rolAp = rolAp;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public Client getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(Client userOrder) {
        this.userOrder = userOrder;
    }



    public StateOrder getOrderState() {
        return orderState;
    }

    public void setOrderState(StateOrder orderState) {
        this.orderState = orderState;
    }

    public List<OrderDetails> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<OrderDetails> menuList) {
        this.menuList = menuList;
    }
}