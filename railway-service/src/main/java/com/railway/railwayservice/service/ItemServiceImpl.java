package com.railway.railwayservice.service;

import com.railway.railwayservice.Exceptions.ItemAllReadyExistingException;
import com.railway.railwayservice.Exceptions.ItemNotFoundException;
import com.railway.railwayservice.Exceptions.RuntimeExceptionHere;
import com.railway.railwayservice.dtos.GoodsCreateRequestDto;
import com.railway.railwayservice.dtos.GoodsEntityResponseDto;
import com.railway.railwayservice.dtos.InventoryPaginationDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.UnitsEntity;
import com.railway.railwayservice.enums.ActiveStatus;
import com.railway.railwayservice.mappers.GoodsMapper;
import com.railway.railwayservice.repository.ItemCategoryRepository;
import com.railway.railwayservice.repository.ItemRepository;
import com.railway.railwayservice.repository.UnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Item service.
 */
@Service
@AllArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {

    private static final String ID_NOT_NULL_ERROR = "id should be null.";
    private static final String SAVE_FAILED = "Saved Failed";
    private static final String GOODS_NAME_ERROR = "Goods Name can not be null or empty";
    private static final String SAVE_SUCCESS = "Saved Successful";
    private static final String ID_NULL_ERROR = "Id can not be null.";
    private static final String ID_MISMATCH_ERROR = "Id mismatch error.";
    private static final String UPDATE_SUCCESS = "Update Successful";
    private static final String DELETE_SUCCESS = "Delete Successful";
    private static final String GET_ITEM_SUCCESS = "Get Item Successful";
    private static final String GET_ALL_ITEM_SUCCESS = "Get All Items Successful";
    private static final String GET_UNITS_SUCCESS = "Get Units Success";
    private static final String GET_ITEM_CATEGORY_SUCCESS = "Get Item Category Success.";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String GET_ITEM_FAILED = "Get Item Failed.";
    private static final String UPDATE_FAILED = "Update Failed";
    private static final String GOOD_NOT_FOUND = "Goods can not found.";

    private final ItemRepository itemRepository;
    private final UnitRepository unitRepository;
    private final ItemCategoryRepository itemCategoryRepository;

    private final GoodsMapper goodsMapper;

    @Override
    public ResponseWrapperDto<GoodsCreateRequestDto> createItem(GoodsCreateRequestDto goodsCreateRequestDto) {
        ResponseWrapperDto<GoodsCreateRequestDto> responseWrapperDto = new ResponseWrapperDto(ActiveStatus.INACTIVE.getValue(), SAVE_FAILED, null);
        if (null != goodsCreateRequestDto.getId()) {
            throw new RuntimeExceptionHere(ID_NOT_NULL_ERROR);
        }
        if (null == goodsCreateRequestDto.getGoodName() || "".equals(goodsCreateRequestDto.getGoodName())) {
            throw new RuntimeExceptionHere(GOODS_NAME_ERROR);
        }
        Optional<GoodsEntity> isExistingGood = itemRepository.findByNameAndIsDeleted(goodsCreateRequestDto.getGoodName(), ActiveStatus.INACTIVE.getValue());
        if (isExistingGood.isPresent()) {
            throw new ItemAllReadyExistingException();
        }

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(goodsCreateRequestDto.getUnitId());

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId((long) goodsCreateRequestDto.getCategory());

        GoodsEntity goodsEntity = goodsMapper.toEntity(goodsCreateRequestDto);
        goodsEntity.setUnitsEntity(unitsEntity);
        goodsEntity.setDate(stringDateToLocalDateTime(goodsCreateRequestDto.getDate()));
        goodsEntity.setCategoryEntity(categoryEntity);

        GoodsEntity response = itemRepository.saveAndFlush(goodsEntity);
        responseWrapperDto = new ResponseWrapperDto(ActiveStatus.ACTIVE.getValue(), SAVE_SUCCESS, goodsMapper.toDto(response));
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto<GoodsEntity> updateItem(GoodsCreateRequestDto goodsCreateRequestDto, Long id) {
        ResponseWrapperDto<GoodsEntity> responseWrapperDto = new ResponseWrapperDto<>(ActiveStatus.INACTIVE.getValue(), UPDATE_FAILED, null);
        if (null == goodsCreateRequestDto.getId()) {
            throw new RuntimeExceptionHere(ID_NULL_ERROR);
        }
        if (id != goodsCreateRequestDto.getId()) {
            throw new RuntimeExceptionHere(ID_MISMATCH_ERROR);
        }
        Optional<GoodsEntity> isExistingGood = itemRepository.findByIdAndIsDeleted(id, ActiveStatus.INACTIVE.getValue());
        if (!isExistingGood.isPresent()) {
            throw new ItemNotFoundException(GOOD_NOT_FOUND);
        }

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(goodsCreateRequestDto.getUnitId());

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId((long) goodsCreateRequestDto.getCategory());

        GoodsEntity goodsEntity = goodsMapper.toEntity(goodsCreateRequestDto);
        goodsEntity.setUnitsEntity(unitsEntity);
        goodsEntity.setCategoryEntity(categoryEntity);

        GoodsEntity response = itemRepository.saveAndFlush(goodsEntity);
        responseWrapperDto = new ResponseWrapperDto<>(ActiveStatus.ACTIVE.getValue(), UPDATE_SUCCESS, response);
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto<GoodsEntity> deleteItem(Long id) {
        ResponseWrapperDto<GoodsEntity> responseWrapperDto;
        Optional<GoodsEntity> isExistingGood = itemRepository.findByIdAndIsDeleted(id, ActiveStatus.INACTIVE.getValue());
        if (!isExistingGood.isPresent()) {
            throw new ItemNotFoundException(GOOD_NOT_FOUND);
        }
        isExistingGood.get().setIsDeleted(ActiveStatus.ACTIVE.getValue());
        GoodsEntity response = itemRepository.saveAndFlush(isExistingGood.get());
        responseWrapperDto = new ResponseWrapperDto<>(ActiveStatus.ACTIVE.getValue(), DELETE_SUCCESS, response);
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto<GoodsEntity> getItem(Long id) {
        ResponseWrapperDto<GoodsEntity> responseWrapperDto = new ResponseWrapperDto<>(ActiveStatus.INACTIVE.getValue(), GET_ITEM_FAILED, null);
        Optional<GoodsEntity> isExisting = itemRepository.findByIdAndIsDeleted(id, ActiveStatus.INACTIVE.getValue());
        if (!isExisting.isPresent()) {
            throw new ItemNotFoundException(GOOD_NOT_FOUND);
        }
        responseWrapperDto = new ResponseWrapperDto<>(ActiveStatus.ACTIVE.getValue(), GET_ITEM_SUCCESS, isExisting.get());
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto<InventoryPaginationDto<List<GoodsEntityResponseDto>>> getAllItem(Integer categoryId, Integer size, Integer page) {
        ResponseWrapperDto<InventoryPaginationDto<List<GoodsEntityResponseDto>>> responseWrapperDto;

        CategoryEntity itemCategory = new CategoryEntity();
        itemCategory.setId((long) categoryId);

        Pageable paging = PageRequest.of(page, size, Sort.by("name"));
        Page<GoodsEntityResponseDto> result = itemRepository.findGoodsEntities(itemCategory, ActiveStatus.INACTIVE.getValue(), paging);
        InventoryPaginationDto<List<GoodsEntityResponseDto>> inventoryPaginationDto = new InventoryPaginationDto<>(
                result.getTotalElements(),
                result.getTotalPages(),
                size,
                page,
                new ArrayList<>());

        if (result.hasContent()) {
            inventoryPaginationDto.setContent(result.getContent());
        }
        responseWrapperDto = new ResponseWrapperDto<>(ActiveStatus.ACTIVE.getValue(), GET_ALL_ITEM_SUCCESS, inventoryPaginationDto);
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto<List<UnitsEntity>> getUnitList() {
        List<UnitsEntity> unitList = unitRepository.findAll();
        return new ResponseWrapperDto<>(ActiveStatus.ACTIVE.getValue(), GET_UNITS_SUCCESS, unitList);
    }

    @Override
    public ResponseWrapperDto<List<CategoryEntity>> getItemCategoryList() {
        List<CategoryEntity> categoryList = itemCategoryRepository.findAll();
        return new ResponseWrapperDto<>(ActiveStatus.ACTIVE.getValue(), GET_ITEM_CATEGORY_SUCCESS, categoryList);
    }

    private LocalDate stringDateToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, formatter);
    }
}