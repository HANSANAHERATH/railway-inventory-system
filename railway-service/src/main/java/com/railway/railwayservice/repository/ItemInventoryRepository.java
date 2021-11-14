package com.railway.railwayservice.repository;

import com.railway.railwayservice.entity.ItemInventory;
import com.railway.railwayservice.entity.ItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemInventoryRepository  extends JpaRepository<ItemInventory, Long> {
    List<ItemInventory> findAllByItemsEntity(ItemsEntity item);
}
