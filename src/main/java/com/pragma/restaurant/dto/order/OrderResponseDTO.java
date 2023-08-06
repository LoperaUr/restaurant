package com.pragma.restaurant.dto.order;

import com.pragma.restaurant.dto.orderDetail.OrderDetailDTO;
import com.pragma.restaurant.entity.Employee;
import com.pragma.restaurant.util.StateOrder;

import java.util.List;

public class OrderResponseDTO extends OrderDTO{
    private Long id;
    private String restaurant;
    private StateOrder orderState;
    private List<OrderDetailDTO> detailDTOS;

    private Employee employee;




    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderDetailDTO> getMenuList() {
        return detailDTOS;
    }

    public void setMenuList(List<OrderDetailDTO> menuList) {
        this.detailDTOS = menuList;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public StateOrder getOrderState() {
        return orderState;
    }

    public void setOrderState(StateOrder orderState) {
        this.orderState = orderState;
    }

    public List<OrderDetailDTO> getDetailDTOS() {
        return detailDTOS;
    }

    public void setDetailDTOS(List<OrderDetailDTO> detailDTOS) {
        this.detailDTOS = detailDTOS;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
