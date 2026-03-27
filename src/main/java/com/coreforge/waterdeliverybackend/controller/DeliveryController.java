package com.coreforge.waterdeliverybackend.controller;

import com.coreforge.waterdeliverybackend.dto.CreateDeliveryRequest;
import com.coreforge.waterdeliverybackend.dto.UpdateDeliveryRequest;
import com.coreforge.waterdeliverybackend.model.Delivery;
import com.coreforge.waterdeliverybackend.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public Delivery createDelivery(@Valid @RequestBody CreateDeliveryRequest request) {
        return deliveryService.createDelivery(request);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Delivery> getByEmployeeId(@PathVariable Long employeeId) {
        return deliveryService.getByEmployeeId(employeeId);
    }

    @PutMapping("/{id}")
    public Delivery updateDelivery(@PathVariable Long id, @Valid @RequestBody UpdateDeliveryRequest request) {
        Delivery delivery = new Delivery();
        delivery.setQuantity(request.getQuantity());
        delivery.setDate(request.getDate());
        delivery.setNotes(request.getNotes());

        return deliveryService.updateDelivery(id, delivery);
    }

    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
    }
}