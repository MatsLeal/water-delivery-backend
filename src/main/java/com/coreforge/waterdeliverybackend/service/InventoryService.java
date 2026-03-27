package com.coreforge.waterdeliverybackend.service;

import com.coreforge.waterdeliverybackend.exception.InsufficientStockException;
import com.coreforge.waterdeliverybackend.model.Inventory;
import com.coreforge.waterdeliverybackend.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);


    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    public Inventory getInventory(){
        log.debug("Fetching inventory record");
        return inventoryRepository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    log.warn("No inventory found, creating default inventory");
                    return createDefaultInventory();
                });
    }

    public Inventory restock(Integer quantity){
        log.info("Restocking inventory by quantity={}", quantity);
        Inventory inventory = getInventory();

        Integer before = inventory.getCurrentQuantity();
        inventory.setCurrentQuantity(before + quantity);
        inventory.setLastUpdated(LocalDateTime.now());

        Inventory saved = inventoryRepository.save(inventory);

        log.info("Inventory restocked: before={}, added={}, after={}", before, quantity, saved.getCurrentQuantity());
        return saved;
    }

    public void consumeStock(Integer quantity){
        log.info("Attempting to consume stock, requestedQuantity={}", quantity);
        Inventory inventory = getInventory();

        Integer current = inventory.getCurrentQuantity();

        if(current < quantity){
            log.warn("Insufficient stock: requestedQuantity={}, currentQuantity={}", quantity, current);
            throw new InsufficientStockException("Insufficient stock to consume");
        }

        inventory.setCurrentQuantity(current - quantity);
        inventory.setLastUpdated(LocalDateTime.now());
        inventoryRepository.save(inventory);

        log.info("Stock consumed successfully: requestedQuantity={}, remainingQuantity={}", quantity, inventory.getCurrentQuantity());
    }

    private Inventory createDefaultInventory(){
        log.info("Creating default inventory record");

        Inventory inventory = Inventory.builder()
                .currentQuantity(0)
                .lastUpdated(LocalDateTime.now())
                .build();

        Inventory saved = inventoryRepository.save(inventory);

        log.info("Default inventory created with id={}, quantity={}", saved.getId(), saved.getCurrentQuantity());
        return saved;
    }
    public Inventory setInventory(Integer quantity) {
        log.info("Setting inventory quantity directly to {}", quantity);

        Inventory inventory = getInventory();
        Integer before = inventory.getCurrentQuantity();

        inventory.setCurrentQuantity(quantity);
        inventory.setLastUpdated(LocalDateTime.now());

        Inventory saved = inventoryRepository.save(inventory);

        log.info("Inventory updated: before={}, after={}", before, saved.getCurrentQuantity());
        return saved;
    }

}