package com.pragma.restaurant.service;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepositoryMock;
    private ClientRepository clientRepositoryMock;
    private MenuRepository menuRepositoryMock;
    private OrderDetailRespository orderDetailRespositoryMock;
    private OrderMapper orderMapperMock;

    @BeforeEach
    public void setUp() {
        orderRepositoryMock = mock(OrderRepository.class);
        clientRepositoryMock = mock(ClientRepository.class);
        menuRepositoryMock = mock(MenuRepository.class);
        orderDetailRespositoryMock = mock(OrderDetailRespository.class);
        orderMapperMock = mock(OrderMapper.class);

        orderService = new OrderService(
                orderRepositoryMock,
                clientRepositoryMock,
                orderMapperMock,
                menuRepositoryMock,
                orderDetailRespositoryMock
        );
    }

    @Test
    public void testCreateOrder() throws Exception {
        // Crear un objeto Order con datos válidos para la prueba
        Order order = new Order();
        order.setRolRequest('C');
        // Establecer otras propiedades necesarias para la orden

        // Configurar el comportamiento simulado para clientRepository y menuRepository
        when(clientRepositoryMock.findById(any())).thenReturn(Optional.empty());
        when(menuRepositoryMock.findById(any())).thenReturn(Optional.of(new Menu()));

        // Configurar el comportamiento simulado para orderRepository.save y orderDetailRespository.save
        when(orderRepositoryMock.save(any(Order.class))).thenReturn(order);
        when(orderDetailRespositoryMock.save(any(OrderDetails.class))).thenReturn(any(OrderDetails.class));

        // Llamar al método que se está probando
        OrderResponseDTO result = orderService.createOrder(order);

        // Verificar el comportamiento esperado
        verify(orderRepositoryMock, times(1)).save(any(Order.class));
        verify(orderDetailRespositoryMock, times(order.getMenuList().size())).save(any(OrderDetails.class));
        // Verificar que el resultado es el esperado
        // Por ejemplo:
        // assertNotNull(result);
        // assertEquals(expectedOrderResponseDTO, result);
    }


    @Test
    public void testGetListOrdersByStateAndRestaurant() throws Exception {
        // Crear una lista de órdenes ficticia para que sea devuelta por el repositorio de órdenes
        List<Order> mockOrders = new ArrayList<>();
        // Agregar órdenes ficticias a la lista
        // ...

        // Configurar el comportamiento simulado para el orderRepository.findByRestaurantAndOrderState method
        when(orderRepositoryMock.findByRestaurantAndOrderState(anyString(), any(StateOrder.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(mockOrders));

        // Configurar el comportamiento simulado para el orderMapper.toDto method
        // Aquí, puedes devolver un OrderResponseDTO ficticio basado en los datos de la Order
        when(orderMapperMock.toDto(any(Order.class))).thenAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
            // Configurar las propiedades de orderResponseDTO basadas en order
            // ...
            return orderResponseDTO;
        });

        // Llamar al método que se está probando con parámetros válidos
        StateOrder state = StateOrder.PENDING;
        int size = 10;
        Page<OrderResponseDTO> result = orderService.getListOrdersByStateAndRestaurant('A', "Restaurante1", state, size);

        // Verificar el comportamiento esperado
        verify(orderRepositoryMock, times(1)).findByRestaurantAndOrderState(anyString(), any(StateOrder.class), any(Pageable.class));
        verify(orderMapperMock, times(mockOrders.size())).toDto(any(Order.class));
        // Verificar que el resultado no sea nulo y tenga el tamaño correcto
        // Por ejemplo:
        // assertNotNull(result);
        // assertEquals(mockOrders.size(), result.getContent().size());
    }

    @Test
    public void testUpdateOrderStateToInPreparation() throws Exception {
        // Crear un objeto Order con datos válidos para la prueba
        Order order = new Order();
        order.setRolAp('A');
        // Establecer otras propiedades necesarias para la orden

        // Configurar el comportamiento simulado para orderRepository.findById
        when(orderRepositoryMock.findById(anyLong())).thenReturn(Optional.of(order));

        // Llamar al método que se está probando con parámetros válidos
        Long orderId = 1L;
        OrderResponseDTO result = orderService.updateOrderStateToInPreparation(orderId, order);

        // Verificar el comportamiento esperado
        verify(orderRepositoryMock, times(1)).findById(eq(orderId));
        verify(orderRepositoryMock, times(1)).save(any(Order.class));
        // Verificar que el resultado no sea nulo
        // Por ejemplo:
        // assertNotNull(result);
    }

    // Write similar test methods for other methods in the OrderService class
}
