package com.coreforge.waterdeliverybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardSummaryResponse {
    private Integer currentStock;
    private Long totalDeliveries;
    private Long deliveriesToday;
    private Integer totalQuantityDelivered;
}