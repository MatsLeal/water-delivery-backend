package com.coreforge.waterdeliverybackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CreateDeliveryRequest {

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Date is required")
    private LocalDate date;

    public CreateDeliveryRequest() {
    }

    public CreateDeliveryRequest(Long employeeId, Integer quantity, LocalDate date) {
        this.employeeId = employeeId;
        this.quantity = quantity;
        this.date = date;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}