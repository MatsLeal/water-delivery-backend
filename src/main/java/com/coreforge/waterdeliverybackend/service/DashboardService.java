package com.coreforge.waterdeliverybackend.service;

import com.coreforge.waterdeliverybackend.dto.DashboardSummaryResponse;
import com.coreforge.waterdeliverybackend.model.Inventory;
import com.coreforge.waterdeliverybackend.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DashboardService {

    private final DeliveryRepository deliveryRepository;
    private final InventoryService inventoryService;

    public DashboardService(DeliveryRepository deliveryRepository,
                            InventoryService inventoryService) {
        this.deliveryRepository = deliveryRepository;
        this.inventoryService = inventoryService;
    }

    public DashboardSummaryResponse getSummary() {
        Inventory inventory = inventoryService.getInventory();

        LocalDate today = LocalDate.now();
        LocalDate startOfDay = LocalDate.from(today.atStartOfDay());
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

        long totalDeliveries = deliveryRepository.count();
        long deliveriesToday = deliveryRepository.countByDateBetween(startOfDay, LocalDate.from(endOfDay));
        Integer totalQuantityDelivered = deliveryRepository.sumAllDeliveredQuantity();

        return DashboardSummaryResponse.builder()
                .currentStock(inventory.getCurrentQuantity())
                .totalDeliveries(totalDeliveries)
                .deliveriesToday(deliveriesToday)
                .totalQuantityDelivered(totalQuantityDelivered == null ? 0 : totalQuantityDelivered)
                .build();
    }
}