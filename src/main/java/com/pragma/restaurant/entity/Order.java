package com.pragma.restaurant.entity;

import com.pragma.restaurant.util.StateOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Character rolRequest;
    private Character rolAp;
    private String restaurant;
    private Integer idUser;

    @Enumerated(EnumType.STRING)
    private StateOrder orderState = StateOrder.PENDING;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order")
    private List<OrderDetails> menuList;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    public List<OrderDetails> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<OrderDetails> menuList) {
        this.menuList = menuList;
    }

    public StateOrder getOrderState() {
        return orderState;
    }

    public void setOrderState(StateOrder orderState) {
        this.orderState = orderState;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
