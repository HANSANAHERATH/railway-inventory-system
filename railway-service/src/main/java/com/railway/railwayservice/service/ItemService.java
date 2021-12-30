package com.railway.railwayservice.service;

import com.railway.railwayservice.dtos.GoodsCreateRequestDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;

public interface ItemService {
    ResponseWrapperDto createItem(GoodsCreateRequestDto goodsCreateRequestDto);

    ResponseWrapperDto updateItem(GoodsCreateRequestDto goodsCreateRequestDto, long id);

    ResponseWrapperDto deleteItem(long id);

    ResponseWrapperDto getItem(long id);

    ResponseWrapperDto getAllItem(int categoryId, int size, int page);

    ResponseWrapperDto getUnitList();

    ResponseWrapperDto getItemCategoryList();
}
