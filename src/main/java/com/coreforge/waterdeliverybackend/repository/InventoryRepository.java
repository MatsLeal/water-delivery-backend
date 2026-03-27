package com.coreforge.waterdeliverybackend.repository;

import com.coreforge.waterdeliverybackend.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InventoryRepository extends JpaRepository<Inventory,Long>{

}