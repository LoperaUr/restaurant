package com.pragma.restaurant.dto.orderDetail;

import com.pragma.restaurant.entity.Menu;
import com.pragma.restaurant.entity.Order;

public class OrderDetailDTO {
    private Menu menuId;
    private Order orderId;
    private Integer quantity;

    public Menu getMenuId() {
        return menuId;
    }

    public void setMenuId(Menu menuId) {
        this.menuId = menuId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
