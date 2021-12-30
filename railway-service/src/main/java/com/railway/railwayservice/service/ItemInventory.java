package com.railway.railwayservice.service;

import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.enums.InventoryFilter;

public interface ItemInventory {
    ResponseWrapperDto getItemAll();

    ResponseWrapperDto getItemLookup(long category) throws Exception;

    ResponseWrapperDto createInventory(CreateInventoryDto createInventoryDto) throws Exception;

    ResponseWrapperDto getAllInventory(Long id, InventoryFilter inventoryFilter,  int page, int size) throws Exception;
}
