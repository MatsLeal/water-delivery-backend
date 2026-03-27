package com.coreforge.waterdeliverybackend.service;

import com.coreforge.waterdeliverybackend.model.Inventory;
import com.coreforge.waterdeliverybackend.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void consumeStock_success_shouldReduceStock() {
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setCurrentQuantity(10);

        when(inventoryRepository.findAll()).thenReturn(List.of(inventory));
        when(inventoryRepository.save(any(Inventory.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        inventoryService.consumeStock(5);

        assertEquals(5, inventory.getCurrentQuantity());
        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    void consumeStock_shouldThrow_whenNotEnoughStockExists() {
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setCurrentQuantity(5);

        when(inventoryRepository.findAll()).thenReturn(List.of(inventory));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> inventoryService.consumeStock(10));

        assertEquals("Insufficient stock to consume", exception.getMessage());
        verify(inventoryRepository, never()).save(any());
    }

}