package com.railway.railwayservice.service;

import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.enums.InventoryFilter;

/**
 * The interface Item inventory.
 */
public interface ItemInventory {
    /**
     * Gets item all.
     *
     * @return the item all
     */
    ResponseWrapperDto getItemAll();

    /**
     * Gets item lookup.
     *
     * @param category the category
     * @return the item lookup
     */
    ResponseWrapperDto getItemLookup(long category);

    /**
     * Create inventory response wrapper dto.
     *
     * @param createInventoryDto the create inventory dto
     * @return the response wrapper dto
     */
    ResponseWrapperDto createInventory(CreateInventoryDto createInventoryDto);

    /**
     * Gets all inventory.
     *
     * @param id              the id
     * @param inventoryFilter the inventory filter
     * @param page            the page
     * @param size            the size
     * @return the all inventory
     */
    ResponseWrapperDto getAllInventory(Long id, InventoryFilter inventoryFilter, int page, int size);
}
