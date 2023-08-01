package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.order.OrderDTO;
import com.pragma.restaurant.dto.order.OrderResponseDTO;
import com.pragma.restaurant.entity.Menu;
import com.pragma.restaurant.entity.Order;
import com.pragma.restaurant.entity.OrderDetails;
import com.pragma.restaurant.mapper.OrderMapper;
import com.pragma.restaurant.repository.ClientRepository;
import com.pragma.restaurant.repository.MenuRepository;
import com.pragma.restaurant.repository.OrderDetailRespository;
import com.pragma.restaurant.repository.OrderRepository;
import com.pragma.restaurant.util.StateOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.pragma.restaurant.validation.OrderValidation.validateRestaurantAndDetails;
import static com.pragma.restaurant.validation.OrderValidation.validateRestaurantIsSame;

@Service
public class OrderService implements BaseService<OrderDTO, Order> {
    private final OrderRepository orderRepository;

    private final ClientRepository clientRepository;

    private final OrderMapper orderMapper;

    private final MenuRepository menuRepository;

    private final OrderDetailRespository orderDetailRespository;

    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, OrderMapper orderMapper, MenuRepository menuRepository, OrderDetailRespository orderDetailRespository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.orderMapper = orderMapper;
        this.menuRepository = menuRepository;
        this.orderDetailRespository = orderDetailRespository;
    }

    public OrderResponseDTO createOrder(Order order) throws Exception {
        try {
            if (order.getRolRequest() != ('C')) {
                throw new Exception("No tiene permisos para crear una orden");
            }
            if (clientRepository.findById(order.getUserOrder().getId()).get().isOrderActive()) {
                throw new Exception("El usuario ya tiene una orden activa");
            }
            if (validateRestaurantAndDetails(order.getRestaurant(), order.getMenuList(), order.getOrderState())) {
                throw new Exception("La sede y los detalles de la orden son obligatorios");
            }

            for (OrderDetails
                    detail :
                    order.getMenuList()
            ) {
                Optional<Menu> menuOptional = menuRepository.findById(detail.getMenuId().getId());
                if (menuOptional.isEmpty()) {
                    throw new Exception("El plato con id " + detail.getMenuId().getId() + " no existe");
                } else if (validateRestaurantIsSame(order.getRestaurant(), menuOptional.get().getRestaurant())) {
                    throw new Exception("Los platos de la orden deben ser del mismo restaurante");
                }

            }


            OrderResponseDTO orderResponseDTO = orderMapper.toDto(orderRepository.save(order));
            for (OrderDetails
                    detail :
                    order.getMenuList()
            ) {
                orderDetailRespository.save(detail);
            }
            return orderResponseDTO;
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

    public OrderResponseDTO updateOrderStateToInPreparation(Long id, Order data) throws Exception {
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

    public OrderResponseDTO updateOrderStateToReady(Long id, Order data) throws Exception {
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

    public OrderResponseDTO updateOrderStateToDelivered(Long id, Order data) throws Exception {
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

    public OrderResponseDTO toCancelOrders(Long id,String reason)throws Exception{
        try {
            Optional<Order> orderOptional = orderRepository.findById(id);
            if(orderOptional.get().getOrderState()!=StateOrder.PENDING) {
                throw new Exception("Lo sentimos, tu pedido ya está en preparación y no puede cancelarse");
            }
            if (orderOptional.isEmpty()) {
                throw new Exception("No existe la orden");
            }else{
                Order order = orderOptional.get();
                order.setOrderState(StateOrder.CANCELLED);
                return orderMapper.toDto(orderRepository.save(order));





            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
