package com.railway.railwayservice.service.impl;

import com.railway.railwayservice.Exceptions.ItemNotFoundException;
import com.railway.railwayservice.Exceptions.ItemQuantityException;
import com.railway.railwayservice.Exceptions.RuntimeExceptionHere;
import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.dtos.GoodNameLookupResponseDto;
import com.railway.railwayservice.dtos.InventoryPaginationDto;
import com.railway.railwayservice.dtos.InventoryResponseDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.InventoryEntity;
import com.railway.railwayservice.enums.ActiveStatus;
import com.railway.railwayservice.enums.InventoryFilter;
import com.railway.railwayservice.enums.InventoryType;
import com.railway.railwayservice.mappers.InventoryMapper;
import com.railway.railwayservice.repository.ItemCategoryRepository;
import com.railway.railwayservice.repository.ItemInventoryRepository;
import com.railway.railwayservice.repository.ItemRepository;
import com.railway.railwayservice.service.ItemInventory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Item inventory service.
 */
@Service
@Transactional
@AllArgsConstructor
public class ItemInventoryServiceImpl implements ItemInventory {

    private static final String FETCH_SUCCESS = "Fetch Success.";
    private static final String CREATE_SUCCESS = "Create Success.";
    private static final String CATEGORY_NOT_FOUND = "Can not found category.";
    private static final String ID_NOT_NULL_ERROR = "Id should be null.";
    private static final String GOOD_NOT_FOUND = "Can not find goods.";

    private final ItemRepository itemRepository;
    private final ItemInventoryRepository itemInventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final ItemCategoryRepository itemCategoryRepository;

    @Override
    public ResponseWrapperDto<List<GoodNameLookupResponseDto>> getItemLookup(Long category) {
        Optional<CategoryEntity> existingCategory = itemCategoryRepository.findById(category);

        if(!existingCategory.isPresent()){
            throw new RuntimeExceptionHere(CATEGORY_NOT_FOUND);
        }
        CategoryEntity itemCategory = new CategoryEntity();
        itemCategory.setId(category);
        List<GoodNameLookupResponseDto> goodNameLookupResponseDtos = itemRepository.findGoodsList(itemCategory, ActiveStatus.INACTIVE.getValue());
        return new ResponseWrapperDto<>(ActiveStatus.ACTIVE.getValue(), FETCH_SUCCESS, goodNameLookupResponseDtos);
    }

    @Override
    public ResponseWrapperDto<CreateInventoryDto> createInventory(CreateInventoryDto createInventoryDto) {
        if (null != createInventoryDto.getId()) {
            throw new RuntimeExceptionHere(ID_NOT_NULL_ERROR);
        }

        Optional<GoodsEntity> existingGoodsEntity = itemRepository.findById(createInventoryDto.getGoodsId());
        if (!existingGoodsEntity.isPresent()) {
            throw new ItemNotFoundException(GOOD_NOT_FOUND);
        }

        InventoryEntity inventoryEntity = inventoryMapper.toEntity(createInventoryDto);

        if (createInventoryDto.getInventoryType().equals(InventoryType.GOODS_IN)) {
            inventoryEntity.setInventoryType(InventoryType.GOODS_IN);
            Float total = existingGoodsEntity.get().getTotalQuantity() + createInventoryDto.getQuantity();
            existingGoodsEntity.get().setTotalQuantity(total);
        } else {
            inventoryEntity.setInventoryType(InventoryType.GOODS_OUT);
            if (existingGoodsEntity.get().getTotalQuantity() < createInventoryDto.getQuantity()) {
                throw new ItemQuantityException();
            }
            Float total = existingGoodsEntity.get().getTotalQuantity() - createInventoryDto.getQuantity();
            existingGoodsEntity.get().setTotalQuantity(total);
        }

        inventoryEntity.setDate(LocalDate.now());
        inventoryEntity.setTime(LocalTime.now());
        inventoryEntity.setUnitsEntity(existingGoodsEntity.get().getUnitsEntity());
        inventoryEntity.setGoodsEntity(existingGoodsEntity.get());

        InventoryEntity savedEntity = itemInventoryRepository.saveAndFlush(inventoryEntity);
        CreateInventoryDto response = inventoryMapper.toDto(savedEntity);

        return new ResponseWrapperDto<>(ActiveStatus.ACTIVE.getValue(), CREATE_SUCCESS, response);
    }

    @Override
    public ResponseWrapperDto<InventoryPaginationDto<List<InventoryResponseDto>>> getAllInventory(Long id, InventoryFilter inventoryFilter, Integer page, Integer size) {
        List<String> inventoryTypes = new ArrayList<>();

        if (inventoryFilter.equals(InventoryFilter.ALL)) {
            inventoryTypes.add(InventoryType.GOODS_IN.toString());
            inventoryTypes.add(InventoryType.GOODS_OUT.toString());
        } else {
            if (inventoryFilter.equals(InventoryFilter.IN))
                inventoryTypes.add(InventoryType.GOODS_IN.toString());
            else
                inventoryTypes.add(InventoryType.GOODS_OUT.toString());
        }
        Pageable paging = PageRequest.of(page, size);
        Page<InventoryResponseDto> result = itemInventoryRepository.findAllByInventory(id, ActiveStatus.INACTIVE.getValue(), inventoryTypes, paging);
        InventoryPaginationDto<List<InventoryResponseDto>> inventoryPaginationDto = new InventoryPaginationDto<>(
                result.getTotalElements(),
                result.getTotalPages(),
                size,
                page,
                new ArrayList<>());

        if (result.hasContent()) {
            inventoryPaginationDto.setContent(result.getContent());
        }
        return new ResponseWrapperDto<>(ActiveStatus.ACTIVE.getValue(), FETCH_SUCCESS, inventoryPaginationDto);
    }
}
