package com.coreforge.waterdeliverybackend.controller;

import com.coreforge.waterdeliverybackend.model.Inventory;
import com.coreforge.waterdeliverybackend.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public Inventory getInventory() {
        return inventoryService.getInventory();
    }

    @PutMapping("/restock")
    public Inventory restock(@RequestParam Integer quantity) {
        return inventoryService.restock(quantity);
    }

    @PutMapping("/set")
    public Inventory setInventory(@RequestParam Integer quantity) {
        return inventoryService.setInventory(quantity);
    }
}