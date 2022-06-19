package com.railway.railwayservice.controllers;

import com.railway.railwayservice.dtos.GoodsCreateRequestDto;
import com.railway.railwayservice.dtos.GoodsEntityResponseDto;
import com.railway.railwayservice.dtos.InventoryPaginationDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.UnitsEntity;
import com.railway.railwayservice.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Items controller.
 */
@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemsController {

    private final ItemService itemService;

    /**
     * Gets item.
     *
     * @param id the id
     * @return the item
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseWrapperDto<GoodsEntity>> getItem(@PathVariable("id") Long id) {
        ResponseWrapperDto<GoodsEntity> responseWrapperDto = itemService.getItem(id);
        return ResponseEntity.ok(responseWrapperDto);
    }

    /**
     * Gets all item.
     *
     * @param categoryId the category id
     * @param size       the size
     * @param page       the page
     * @return the all item
     */
    @GetMapping(path = "/all/{id}/{size}/{page}")
    public ResponseEntity<ResponseWrapperDto<InventoryPaginationDto<List<GoodsEntityResponseDto>>>> getAllItem(@PathVariable("id") Integer categoryId,
                                                                                                               @PathVariable("size") Integer size,
                                                                                                               @PathVariable("page") Integer page) {
        ResponseWrapperDto<InventoryPaginationDto<List<GoodsEntityResponseDto>>> responseWrapperDto = itemService.getAllItem(categoryId, size, page);
        return ResponseEntity.ok(responseWrapperDto);
    }

    /**
     * Create item response entity.
     *
     * @param goodsCreateRequestDto the goods create request dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<ResponseWrapperDto<GoodsCreateRequestDto>> createItem(@RequestBody GoodsCreateRequestDto goodsCreateRequestDto) {
        ResponseWrapperDto<GoodsCreateRequestDto> responseWrapperDto = itemService.createItem(goodsCreateRequestDto);
        return ResponseEntity.ok(responseWrapperDto);
    }

    /**
     * Update item response entity.
     *
     * @param goodsCreateRequestDto the goods create request dto
     * @param id                    the id
     * @return the response entity
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseWrapperDto<GoodsEntity>> updateItem(@RequestBody GoodsCreateRequestDto goodsCreateRequestDto, @PathVariable("id") Long id) {
        ResponseWrapperDto<GoodsEntity> responseWrapperDto = itemService.updateItem(goodsCreateRequestDto, id);
        return ResponseEntity.ok(responseWrapperDto);
    }

    /**
     * Delete item response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseWrapperDto<GoodsEntity>> deleteItem(@PathVariable("id") Long id) {
        ResponseWrapperDto<GoodsEntity> responseWrapperDto = itemService.deleteItem(id);
        return ResponseEntity.ok(responseWrapperDto);
    }

    /**
     * Get unit list response entity.
     *
     * @return the response entity
     */
    @GetMapping(path = "/unitList")
    public ResponseEntity<ResponseWrapperDto<List<UnitsEntity>>> getUnitList() {
        ResponseWrapperDto<List<UnitsEntity>> responseWrapperDto = itemService.getUnitList();
        return ResponseEntity.ok(responseWrapperDto);
    }

    /**
     * Get item category list response entity.
     *
     * @return the response entity
     */
    @GetMapping(path = "/itemCategoryList")
    public ResponseEntity<ResponseWrapperDto<List<CategoryEntity>>> getItemCategoryList() {
        ResponseWrapperDto<List<CategoryEntity>> responseWrapperDto = itemService.getItemCategoryList();
        return ResponseEntity.ok(responseWrapperDto);
    }
}
