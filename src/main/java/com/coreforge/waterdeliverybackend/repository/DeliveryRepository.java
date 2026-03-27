package com.coreforge.waterdeliverybackend.repository;

import com.coreforge.waterdeliverybackend.model.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByEmployeeId(Long employeeId);
    long countByDateBetween(LocalDate date, LocalDate date2);

    @Query("select coalesce(sum(d.quantity), 0) from Delivery d")
    Integer sumAllDeliveredQuantity();

    Page<Delivery> findAll(Pageable pageable);

    Page<Delivery> findByEmployeeId(Long employeeId, Pageable pageable);
}