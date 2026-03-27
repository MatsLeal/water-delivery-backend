package com.coreforge.waterdeliverybackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class CreateDeliveryRequest {

    @Getter
    @Setter
    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @Setter
    @Getter
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @Setter
    @Getter
    @NotNull(message = "Date is required")
    private LocalDate date;

    @Getter
    @Setter
    @NotNull(message = "Notes are required")
    private String notes;

    public CreateDeliveryRequest() {
    }

    public CreateDeliveryRequest(Long employeeId, Integer quantity, LocalDate date) {
        this.employeeId = employeeId;
        this.quantity = quantity;
        this.date = date;
    }

}