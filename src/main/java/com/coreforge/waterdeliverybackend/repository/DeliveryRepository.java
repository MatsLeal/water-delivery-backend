package com.coreforge.waterdeliverybackend.repository;

import com.coreforge.waterdeliverybackend.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByEmployeeId(Long employeeId);
}