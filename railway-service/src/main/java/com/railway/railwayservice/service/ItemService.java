package com.railway.railwayservice.service;

import com.railway.railwayservice.dtos.GoodsCreateRequestDto;
import com.railway.railwayservice.dtos.GoodsEntityResponseDto;
import com.railway.railwayservice.dtos.InventoryPaginationDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.UnitsEntity;

import java.util.List;

/**
 * The interface Item service.
 */
public interface ItemService {
    /**
     * Create item response wrapper dto.
     *
     * @param goodsCreateRequestDto the goods create request dto
     * @return the response wrapper dto
     */
    ResponseWrapperDto<GoodsCreateRequestDto> createItem(GoodsCreateRequestDto goodsCreateRequestDto);

    /**
     * Update item response wrapper dto.
     *
     * @param goodsCreateRequestDto the goods create request dto
     * @param id                    the id
     * @return the response wrapper dto
     */
    ResponseWrapperDto<GoodsEntity> updateItem(GoodsCreateRequestDto goodsCreateRequestDto, Long id);

    /**
     * Delete item response wrapper dto.
     *
     * @param id the id
     * @return the response wrapper dto
     */
    ResponseWrapperDto<GoodsEntity> deleteItem(Long id);

    /**
     * Gets item.
     *
     * @param id the id
     * @return the item
     */
    ResponseWrapperDto<GoodsEntity> getItem(Long id);

    /**
     * Gets all item.
     *
     * @param categoryId the category id
     * @param size       the size
     * @param page       the page
     * @return the all item
     */
    ResponseWrapperDto<InventoryPaginationDto<List<GoodsEntityResponseDto>>> getAllItem(Integer categoryId, Integer size, Integer page);

    /**
     * Gets unit list.
     *
     * @return the unit list
     */
    ResponseWrapperDto<List<UnitsEntity>> getUnitList();

    /**
     * Gets item category list.
     *
     * @return the item category list
     */
    ResponseWrapperDto<List<CategoryEntity>> getItemCategoryList();
}
