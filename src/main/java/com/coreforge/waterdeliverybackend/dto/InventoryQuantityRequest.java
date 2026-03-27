package com.coreforge.waterdeliverybackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryQuantityRequest {

    @NotNull(message = "quantity is required")
    @Min(value = 0, message = "quantity must be at least 0")
    private Integer quantity;
}