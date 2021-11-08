package com.railway.railwayservice.service;

import com.railway.railwayservice.dtos.ItemCreateRequestDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;

public interface ItemService {
    ResponseWrapperDto createItem(ItemCreateRequestDto itemCreateRequestDto);

    ResponseWrapperDto updateItem(ItemCreateRequestDto itemCreateRequestDto, long id);

    ResponseWrapperDto deleteItem(long id);

    ResponseWrapperDto getItem(long id);

    ResponseWrapperDto getAllItem();
}
