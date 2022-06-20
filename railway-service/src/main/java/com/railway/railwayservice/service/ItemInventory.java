package com.railway.railwayservice.service;

import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.dtos.GoodNameLookupResponseDto;
import com.railway.railwayservice.dtos.InventoryPaginationDto;
import com.railway.railwayservice.dtos.InventoryResponseDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.enums.InventoryFilter;

import java.util.List;

/**
 * The interface Item inventory.
 */
public interface ItemInventory {

    /**
     * Gets item lookup.
     *
     * @param category the category
     * @return the item lookup
     */
    ResponseWrapperDto<List<GoodNameLookupResponseDto>> getItemLookup(Long category);

    /**
     * Create inventory response wrapper dto.
     *
     * @param createInventoryDto the create inventory dto
     * @return the response wrapper dto
     */
    ResponseWrapperDto<CreateInventoryDto> createInventory(CreateInventoryDto createInventoryDto);

    /**
     * Gets all inventory.
     *
     * @param id              the id
     * @param inventoryFilter the inventory filter
     * @param page            the page
     * @param size            the size
     * @return the all inventory
     */
    ResponseWrapperDto<InventoryPaginationDto<List<InventoryResponseDto>>> getAllInventory(Long id, InventoryFilter inventoryFilter, Integer page, Integer size);
}
