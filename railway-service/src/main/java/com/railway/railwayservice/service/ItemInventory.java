package com.railway.railwayservice.service;

import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;

public interface ItemInventory {
    ResponseWrapperDto getItemAll();

    ResponseWrapperDto getItemLookup(String itemName) throws Exception;

    ResponseWrapperDto createInventory(CreateInventoryDto createInventoryDto) throws Exception;

    ResponseWrapperDto getAllInventory(Long id) throws Exception;
}
