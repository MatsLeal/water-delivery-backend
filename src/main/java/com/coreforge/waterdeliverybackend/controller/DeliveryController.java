package com.coreforge.waterdeliverybackend.controller;

import com.coreforge.waterdeliverybackend.dto.CreateDeliveryRequest;
import com.coreforge.waterdeliverybackend.dto.PagedResponse;
import com.coreforge.waterdeliverybackend.dto.UpdateDeliveryRequest;
import com.coreforge.waterdeliverybackend.model.Delivery;
import com.coreforge.waterdeliverybackend.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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

        return deliveryService.updateDelivery(id, delivery);
    }
    @GetMapping
    public PagedResponse<Delivery> getDeliveries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long employeeId
    ) {
        Page<Delivery> deliveryPage = deliveryService.getDeliveries(page, size, employeeId);

        return PagedResponse.<Delivery>builder()
                .content(deliveryPage.getContent())
                .page(deliveryPage.getNumber())
                .size(deliveryPage.getSize())
                .totalElements(deliveryPage.getTotalElements())
                .totalPages(deliveryPage.getTotalPages())
                .build();
    }
    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
    }
}