package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.order.OrderResponseDTO;
import com.pragma.restaurant.entity.Menu;
import com.pragma.restaurant.entity.Order;
import com.pragma.restaurant.entity.OrderDetails;
import com.pragma.restaurant.mapper.OrderMapper;
import com.pragma.restaurant.repository.MenuRepository;
import com.pragma.restaurant.repository.OrderRepository;
import com.pragma.restaurant.util.StateOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.pragma.restaurant.validation.OrderValidation.validateRestaurantAndDetails;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final MenuRepository menuRepository;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.menuRepository = menuRepository;
    }

    public OrderResponseDTO createOrder(Order order) throws Exception {
        try {
            if (order.getRolRequest() != ('C')) {
                throw new Exception("No tiene permisos para crear una orden");
            }
            if (orderRepository.findByIdUser(order.getIdUser()).getOrderState().equals(StateOrder.PENDING)) {
                throw new Exception("El usuario ya tiene una orden PENDIENTE");
            }
            if (orderRepository.findByIdUser(order.getIdUser()).getOrderState().equals(StateOrder.IN_PREPARATION)) {
                throw new Exception("El usuario ya tiene una orden EN PREPARACIÓN");
            }
            if (orderRepository.findByIdUser(order.getIdUser()).getOrderState().equals(StateOrder.READY)) {
                throw new Exception("El usuario ya tiene una orden LISTA");
            }
            /*if (validateRestaurantIsSame(order, order.getRestaurant())) {
                throw new Exception("La orden no pertenece al restaurante");
            }*/
            if (validateRestaurantAndDetails(order)) {
                throw new Exception("La sede y los detalles de la orden son obligatorios");
            }
            for (OrderDetails detail : order.getMenuList()) {
                Long id = detail.getMenu().getId();
                Optional<Menu> menuOptional = menuRepository.findById(id);
                detail.getMenu().setName(menuOptional.get().getName());
            }
            return orderMapper.toDto(orderRepository.save(order));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Page<OrderResponseDTO> getListOrdersByStateAndRestaurant(Character rol, String restaurant, StateOrder state, int size) throws Exception {
        try {
            if (rol != ('A')) {
                throw new Exception("No tiene permisos para listar las ordenes");
            }
            Pageable pageable = Pageable.ofSize(size);
            Page<Order> orders = orderRepository.findByRestaurantAndOrderState(restaurant, state, pageable);
            return orders.map(orderMapper::toDto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public OrderResponseDTO updateOrderStateToInPreparation(Integer id, Order data) throws Exception {
        try {
            if (data.getRolAp() != ('A')) {
                throw new Exception("No tiene permisos para actualizar una orden");
            }
            Optional<Order> orderOptional = orderRepository.findById(id);
            if (orderOptional.isEmpty()) {
                throw new Exception("No existe la orden");
            }
            Order order = orderOptional.get();
            order.setOrderState(StateOrder.IN_PREPARATION);
            return orderMapper.toDto(orderRepository.save(order));

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public OrderResponseDTO updateOrderStateToReady(Integer id, Order data) throws Exception {
        try {
            if (data.getRolAp() != ('A')) {
                throw new Exception("No tiene permisos para actualizar una orden");
            }
            Optional<Order> orderOptional = orderRepository.findById(id);
            if (orderOptional.isEmpty()) {
                throw new Exception("No existe la orden");
            }
            Order order = orderOptional.get();
            order.setOrderState(StateOrder.READY);
            return orderMapper.toDto(orderRepository.save(order));

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public OrderResponseDTO updateOrderStateToDelivered(Integer id, Order data) throws Exception {
        try {
            if (data.getRolAp() != ('A')) {
                throw new Exception("No tiene permisos para actualizar una orden");
            }
            Optional<Order> orderOptional = orderRepository.findById(id);
            if (orderOptional.isEmpty()) {
                throw new Exception("No existe la orden");
            }
            Order order = orderOptional.get();
            order.setOrderState(StateOrder.DELIVERED);
            return orderMapper.toDto(orderRepository.save(order));

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}