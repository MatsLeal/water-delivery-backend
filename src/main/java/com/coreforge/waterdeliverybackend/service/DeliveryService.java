package com.coreforge.waterdeliverybackend.service;

import com.coreforge.waterdeliverybackend.dto.CreateDeliveryRequest;
import com.coreforge.waterdeliverybackend.exception.ResourceNotFoundException;
import com.coreforge.waterdeliverybackend.model.Delivery;
import com.coreforge.waterdeliverybackend.model.Employee;
import com.coreforge.waterdeliverybackend.repository.DeliveryRepository;
import com.coreforge.waterdeliverybackend.repository.EmployeeRepository;
import com.coreforge.waterdeliverybackend.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DeliveryService {

    private static final Logger log = LoggerFactory.getLogger(DeliveryService.class);

    private final DeliveryRepository deliveryRepository;
    private final EmployeeRepository employeeRepository;
    private final InventoryService inventoryService;

    public DeliveryService(DeliveryRepository deliveryRepository,
                           EmployeeRepository employeeRepository,
                           InventoryService inventoryService) {
        this.deliveryRepository = deliveryRepository;
        this.employeeRepository = employeeRepository;
        this.inventoryService = inventoryService;
    }
    @Transactional
    public Delivery createDelivery(CreateDeliveryRequest request) {
        log.info("Creating delivery for employeeId={}, quantity={}", request.getEmployeeId(), request.getQuantity());

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> {
                    log.warn("Employee not found for delivery creation, employeeId={}", request.getEmployeeId());
                    return new ResourceNotFoundException("Employee not found with id: " + request.getEmployeeId());
                });

        log.info("Employee found for delivery creation, employeeId={}", employee.getId());

        log.info("Attempting to consume stock for delivery, quantity={}", request.getQuantity());
        inventoryService.consumeStock(request.getQuantity());

        Delivery delivery = new Delivery();
        delivery.setEmployeeId(employee.getId());
        delivery.setQuantity(request.getQuantity());
        delivery.setDate(request.getDate());
        delivery.setNotes(request.getNotes());

        Delivery saved = deliveryRepository.save(delivery);
        log.info("Delivery created successfully, deliveryId={}, employeeId={}, quantity={}",
                saved.getId(), request.getEmployeeId(), request.getQuantity());
        return saved;
    }
    public List<Delivery> getByEmployeeId(Long employeeId) {
        log.info("Fetching deliveries for employeeId={}", employeeId);
        List<Delivery> deliveries = deliveryRepository.findByEmployeeId(employeeId);
        log.info("Found {} delivery records for employeeId={}", deliveries.size(), employeeId);
        return deliveries;
    }

    public Delivery updateDelivery(Long id, Delivery updated) {
        log.info("Updating delivery, id={}", id);
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Delivery not found for update, id={}", id);
                    return new ResourceNotFoundException("Delivery not found with id: " + id);
                });

        delivery.setQuantity(updated.getQuantity());
        delivery.setDate(updated.getDate());

        Delivery saved = deliveryRepository.save(delivery);
        log.info("Delivery updated successfully, id={}", saved.getId());
        return saved;
    }

    public List<Delivery> getAllDeliveries() {
        log.info("Fetching all deliveries");
        List<Delivery> deliveries = deliveryRepository.findAll();
        log.info("Found {} total delivery records", deliveries.size());
        return deliveries;
    }

    public void deleteDelivery(Long id) {
        log.info("Attempting to delete delivery, id={}", id);

        if (!deliveryRepository.existsById(id)) {
            log.warn("Delivery not found for deletion, id={}", id);
            throw new ResourceNotFoundException("Delivery not found with id: " + id);
        }

        deliveryRepository.deleteById(id);
        log.info("Delivery deleted successfully, id={}", id);
    }
}