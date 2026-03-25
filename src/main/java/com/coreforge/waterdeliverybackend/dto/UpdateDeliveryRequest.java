package com.coreforge.waterdeliverybackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UpdateDeliveryRequest {

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Date is required")
    private LocalDate date;

    public UpdateDeliveryRequest() {
    }

    public UpdateDeliveryRequest(Integer quantity, LocalDate date) {
        this.quantity = quantity;
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}