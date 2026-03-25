package com.coreforge.waterdeliverybackend.service;

import com.coreforge.waterdeliverybackend.model.Delivery;
import com.coreforge.waterdeliverybackend.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getByEmployeeId(Long employeeId) {
        return deliveryRepository.findByEmployeeId(employeeId);
    }

    public Delivery updateDelivery(Long id, Delivery updated) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setQuantity(updated.getQuantity());
        delivery.setDate(updated.getDate());

        return deliveryRepository.save(delivery);
    }
}