package com.pragma.restaurant.service;

import com.pragma.restaurant.dto.order.OrderDTO;
import com.pragma.restaurant.dto.order.OrderResponseDTO;
import com.pragma.restaurant.entity.*;
import com.pragma.restaurant.mapper.OrderMapper;
import com.pragma.restaurant.repository.*;
import com.pragma.restaurant.util.SmsAlert;
import com.pragma.restaurant.util.StateOrder;
import com.pragma.restaurant.validation.OrderValidation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.pragma.restaurant.validation.OrderValidation.*;

@Service
public class OrderService implements BaseService<OrderDTO, Order> {

    private static final String SMS_PATH = "/src/main/resources/sms.txt";
    private final OrderRepository orderRepository;

    private final ClientRepository clientRepository;

    private final OrderMapper orderMapper;

    private final MenuRepository menuRepository;

    private final OrderDetailRespository orderDetailRespository;

    private final EmployeeRepository employeeRepository;

    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, OrderMapper orderMapper, MenuRepository menuRepository, OrderDetailRespository orderDetailRespository, EmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.orderMapper = orderMapper;
        this.menuRepository = menuRepository;
        this.orderDetailRespository = orderDetailRespository;
        this.employeeRepository = employeeRepository;
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

    public Page<OrderResponseDTO> getListOrdersByState(Character rol, StateOrder state, int size) throws Exception {
        try {
            if (rol != ('A')) {
                throw new Exception("No tiene permisos para listar las ordenes");
            }
            Pageable pageable = Pageable.ofSize(size);
            Page<Order> orders = orderRepository.findByOrderState(state, pageable);
            return orders.map(orderMapper::toDto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public OrderResponseDTO updateOrderStateToInPreparation(Long id, Character rol, Long employee) throws Exception {
        try {
            if (rol != ('A')) {
                throw new Exception("No tiene permisos para actualizar una orden");
            }
            Optional<Order> orderOptional = orderRepository.findById(id);
            if (orderOptional.isEmpty()) {
                throw new Exception("No existe la orden");
            }
            Order order = orderOptional.get();
            order.setOrderState(StateOrder.IN_PREPARATION);
            assignOrderToEmployee(id, employee);
            toSendSmsAlerts(id, order);
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
            if (data.getOrderState().equals(StateOrder.DELIVERED)) {
                if (!data.getOrderState().equals(StateOrder.READY)) {
                    throw new Exception("No se puede modificar el estado a pendiente o en preparacion");
                }
                order.setOrderState(StateOrder.READY);
                data.setUniqueId(order.getUniqueId());
                if (data.getEndDate() != null) {
                    Long startDate = order.getStartDate().getTime();
                    Long endDate = data.getEndDate().getTime();
                    OrderValidation.getTimeBetweenDates(startDate, endDate);
                }
            }
            toSendSmsAlerts(id, order);
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
            if (data.getOrderState().equals(StateOrder.READY)) {
                order.setOrderState(StateOrder.DELIVERED);
            }

            return orderMapper.toDto(orderRepository.save(order));

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public OrderResponseDTO updateOrderStateToCancelled(Long id) throws Exception {
        try {
            Optional<Order> orderOptional = orderRepository.findById(id);
            Order order = orderOptional.get();
            if (order.getOrderState() != StateOrder.PENDING) {
                throw new Exception("Lo sentimos, tu pedido ya está en preparación y no puede cancelarse");
            }

            order.setOrderState(StateOrder.CANCELLED);
            return orderMapper.toDto(orderRepository.save(order));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public OrderResponseDTO toSendSmsAlerts(Long id, Order orderRegistry) throws Exception {
        try {
            Optional<Order> orderOptional = orderRepository.findById(id);

            if (orderOptional.isEmpty()) {
                throw new Exception("No existe la orden");
            }

            Order order = orderOptional.get();
            SmsAlert smsAlert;

            if (order.getOrderState() == StateOrder.PENDING) {
                smsAlert = SmsAlert.PENDING;
            } else if (order.getOrderState() == StateOrder.READY) {
                smsAlert = SmsAlert.READY;
            } else if (order.getOrderState() == StateOrder.IN_PREPARATION) {
                smsAlert = SmsAlert.IN_PREPARATION;
            } else if (order.getOrderState() == StateOrder.CANCELLED) {
                smsAlert = SmsAlert.CANCELLED;
            } else {
                throw new Exception("Estado de orden inválido");
            }

            order.setSmsAlert(smsAlert);
            orderRepository.save(order);

            String registry = getRegistryMessage(smsAlert, order);
            writeSmsToLogFile(registry);

            return orderMapper.toDto(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getRegistryMessage(SmsAlert smsAlert, Order order) {

        String message;
        switch (smsAlert) {
            case PENDING:
                message = "Tu pedido: " + order.getId() + "está en lista de pendientes " + order.getRestaurant() + order.getMenuList();
                break;
            case READY:
                message = "tu pedido: " + order.getId() + "está listo, prepárate para recibirlo. " + order.getRestaurant() + order.getId() + order.getMenuList();
                break;
            case IN_PREPARATION:
                message = "tu pedido: " + order.getId() + "está siendo preparado. " + order.getRestaurant() + order.getMenuList();
                break;

            case DELIVERED:
                message = "tu pedido: " + order.getId() + "ha sido entregado. " + order.getRestaurant() + order.getId() + order.getMenuList();
                break;
            case CANCELLED:
                message = "tu pedido " + order.getId() + "ha sido cancelado. por el motivo: ";//+order.getClaim();
                break;
            default:
                message = "";
                break;
        }
        return message;
    }

    private void writeSmsToLogFile(String message) throws IOException {
        Path filePath = Paths.get(System.getProperty("user.dir") + SMS_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString(), true))) {
            writer.write(message);
            writer.newLine();
        }
    }


    public void assignOrderToEmployee(Long id, Long idEmployee) throws Exception {
        try {
            Optional<Order> orderOptional = orderRepository.findById(id);
            if (orderOptional.isEmpty()) {
                throw new EntityNotFoundException("Order not found with ID: " + id);
            }
            Optional<Employee> optionalEmployee = employeeRepository.findById(idEmployee);
            if (optionalEmployee.isEmpty()) {
                throw new EntityNotFoundException("Employee not found with ID: " + idEmployee);
            }
            Order order = orderOptional.get();
            Employee employee = optionalEmployee.get();
            order.setEmployeeId(employee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
