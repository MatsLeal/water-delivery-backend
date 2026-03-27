package com.coreforge.waterdeliverybackend.service;

import com.coreforge.waterdeliverybackend.dto.CreateDeliveryRequest;
import com.coreforge.waterdeliverybackend.exception.ResourceNotFoundException;
import com.coreforge.waterdeliverybackend.model.Delivery;
import com.coreforge.waterdeliverybackend.model.Employee;
import com.coreforge.waterdeliverybackend.repository.DeliveryRepository;
import com.coreforge.waterdeliverybackend.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private DeliveryService deliveryService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setName("Test Employee");
    }

    @Test
    void createDelivery_success_shouldSaveDeliveryAndConsumeStock() {
        Long employeeId = 1L;
        Integer quantity = 3;

        CreateDeliveryRequest request = new CreateDeliveryRequest();
        request.setEmployeeId(employeeId);
        request.setDate(LocalDate.from(LocalDateTime.now()));
        request.setQuantity(quantity);
        request.setNotes("Test delivery");

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.of(employee));

        when(deliveryRepository.save(any(Delivery.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Delivery result = deliveryService.createDelivery(request);

        assertNotNull(result);
        assertEquals(quantity, result.getQuantity());
        assertEquals("Test delivery", result.getNotes());

        verify(inventoryService, times(1)).consumeStock(quantity);
        verify(deliveryRepository, times(1)).save(any(Delivery.class));
    }

    @Test
    void createDelivery_shouldThrow_whenEmployeeNotFound() {
        Long employeeId = 99L;

        CreateDeliveryRequest request = new CreateDeliveryRequest();
        request.setEmployeeId(employeeId);
        request.setDate(LocalDate.from(LocalDateTime.now()));
        request.setQuantity(3);
        request.setNotes("Should fail");

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> deliveryService.createDelivery(request)
        );

        assertEquals("Employee not found with id: 99", exception.getMessage());

        verify(inventoryService, never()).consumeStock(any());
        verify(deliveryRepository, never()).save(any());
    }

    @Test
    void createDelivery_shouldThrow_whenNotEnoughStock() {
        Long employeeId = 1L;
        Integer quantity = 5;

        CreateDeliveryRequest request = new CreateDeliveryRequest();
        request.setEmployeeId(employeeId);
        request.setDate(LocalDate.from(LocalDateTime.now()));
        request.setQuantity(quantity);
        request.setNotes("Should fail");

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.of(employee));

        doThrow(new RuntimeException("Not enough stock available"))
                .when(inventoryService).consumeStock(quantity);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> deliveryService.createDelivery(request)
        );

        assertEquals("Not enough stock available", exception.getMessage());

        verify(deliveryRepository, never()).save(any());
    }
}