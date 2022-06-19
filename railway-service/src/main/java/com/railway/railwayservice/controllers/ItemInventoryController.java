package com.railway.railwayservice.controllers;

import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.enums.InventoryFilter;
import com.railway.railwayservice.service.ItemInventory;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Item inventory controller.
 */
@RestController
@RequestMapping("/item-inventory")
@AllArgsConstructor
public class ItemInventoryController {

    private final ItemInventory itemInventory;

    /**
     * Gets item lookup.
     *
     * @param category the category
     * @return the item lookup
     */
    @GetMapping("/lookup/{category}")
    public ResponseEntity<ResponseWrapperDto> getItemLookup(@PathVariable("category") long category) {
        ResponseWrapperDto responseWrapperDto = itemInventory.getItemLookup(category);
        return ResponseEntity.ok(responseWrapperDto);
    }

    /**
     * Create inventory response entity.
     *
     * @param createInventoryDto the create inventory dto
     * @return the response entity
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseWrapperDto> createInventory(@RequestBody CreateInventoryDto createInventoryDto) {
        ResponseWrapperDto responseWrapperDto = itemInventory.createInventory(createInventoryDto);
        return ResponseEntity.ok(responseWrapperDto);
    }

    /**
     * Gets all inventory.
     *
     * @param id              the id
     * @param inventoryFilter the inventory filter
     * @param size            the size
     * @param page            the page
     * @return the all inventory
     */
    @GetMapping("/getAll/{id}/{filter}/{size}/{page}")
    public ResponseEntity<ResponseWrapperDto> getAllInventory(@PathVariable("id") Long id, @PathVariable("filter") InventoryFilter inventoryFilter,
                                                              @PathVariable("size") int size, @PathVariable("page") int page) {
        ResponseWrapperDto responseWrapperDto = itemInventory.getAllInventory(id, inventoryFilter, page, size);
        return ResponseEntity.ok(responseWrapperDto);
    }
}
