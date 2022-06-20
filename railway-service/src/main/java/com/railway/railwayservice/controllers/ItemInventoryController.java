package com.railway.railwayservice.controllers;

import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.dtos.GoodNameLookupResponseDto;
import com.railway.railwayservice.dtos.InventoryPaginationDto;
import com.railway.railwayservice.dtos.InventoryResponseDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.enums.InventoryFilter;
import com.railway.railwayservice.service.ItemInventory;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ResponseWrapperDto<List<GoodNameLookupResponseDto>>> getItemLookup(@PathVariable("category") long category) {
        ResponseWrapperDto<List<GoodNameLookupResponseDto>> responseWrapperDto = itemInventory.getItemLookup(category);
        return ResponseEntity.ok(responseWrapperDto);
    }

    /**
     * Create inventory response entity.
     *
     * @param createInventoryDto the create inventory dto
     * @return the response entity
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseWrapperDto<CreateInventoryDto>> createInventory(@RequestBody CreateInventoryDto createInventoryDto) {
        ResponseWrapperDto<CreateInventoryDto> responseWrapperDto = itemInventory.createInventory(createInventoryDto);
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
    public ResponseEntity<ResponseWrapperDto<InventoryPaginationDto<List<InventoryResponseDto>>>> getAllInventory(@PathVariable("id") Long id, @PathVariable("filter") InventoryFilter inventoryFilter,
                                                                                                                  @PathVariable("size") Integer size, @PathVariable("page") Integer page) {
        ResponseWrapperDto<InventoryPaginationDto<List<InventoryResponseDto>>> responseWrapperDto = itemInventory.getAllInventory(id, inventoryFilter, page, size);
        return ResponseEntity.ok(responseWrapperDto);
    }
}
