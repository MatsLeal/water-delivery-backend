package com.coreforge.waterdeliverybackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="current_quantity" , nullable = false)
    private Integer currentQuantity;

    @Column(name="last_updated" , nullable = false)
    private LocalDateTime lastUpdated;

}