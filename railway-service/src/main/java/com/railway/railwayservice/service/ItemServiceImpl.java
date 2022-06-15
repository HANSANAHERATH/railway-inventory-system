package com.railway.railwayservice.service;

import com.railway.railwayservice.Exceptions.ItemAllReadyExistingException;
import com.railway.railwayservice.Exceptions.ItemNotFoundException;
import com.railway.railwayservice.Exceptions.RuntimeExceptionHere;
import com.railway.railwayservice.dtos.GoodsEntityResponseDto;
import com.railway.railwayservice.dtos.InventoryPaginationDto;
import com.railway.railwayservice.dtos.GoodsCreateRequestDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.UnitsEntity;
import com.railway.railwayservice.repository.ItemCategoryRepository;
import com.railway.railwayservice.repository.ItemRepository;
import com.railway.railwayservice.repository.UnitRepository;
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

@Service
@Transactional
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    private final UnitRepository unitRepository;

    private final ItemCategoryRepository itemCategoryRepository;

    public ItemServiceImpl(ItemRepository itemRepository, UnitRepository unitRepository, ItemCategoryRepository itemCategoryRepository) {
        this.itemRepository = itemRepository;
        this.unitRepository = unitRepository;
        this.itemCategoryRepository = itemCategoryRepository;
    }

    @Transactional
    @Override
    public ResponseWrapperDto createItem(GoodsCreateRequestDto goodsCreateRequestDto) throws RuntimeExceptionHere {
        try {
            ResponseWrapperDto responseWrapperDto;
            Optional<GoodsEntity> isExistingGood = itemRepository.findByNameAndIsDeleted(goodsCreateRequestDto.getGoodName(), false);

            if(isExistingGood.isPresent()){
                throw new ItemAllReadyExistingException();
            }

            UnitsEntity unitsEntity = new UnitsEntity();
            unitsEntity.setId(goodsCreateRequestDto.getUnitId());

            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId((long) goodsCreateRequestDto.getCategory());

            GoodsEntity goodsEntity = new GoodsEntity();
            goodsEntity.setName(goodsCreateRequestDto.getGoodName());
            goodsEntity.setDescription(goodsCreateRequestDto.getDescription());
            goodsEntity.setUnitsEntity(unitsEntity);
            goodsEntity.setDate(stringDateToLocalDateTime(goodsCreateRequestDto.getDate()));
            goodsEntity.setCategoryEntity(categoryEntity);
            goodsEntity.setMinQuantity(goodsCreateRequestDto.getMinQuantity());
            GoodsEntity response = itemRepository.saveAndFlush(goodsEntity);

            if(response.getId() != null){
                responseWrapperDto = new ResponseWrapperDto(true, "Saved Successful", null);
            } else{
                responseWrapperDto = new ResponseWrapperDto(false, "Saved Failed", null);
            }

            return responseWrapperDto;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeExceptionHere(e);
        }
    }

    @Transactional
    @Override
    public ResponseWrapperDto<GoodsEntity> updateItem(GoodsCreateRequestDto goodsCreateRequestDto, long id) {
        ResponseWrapperDto<GoodsEntity> responseWrapperDto;
        Optional<GoodsEntity> isExistingGood = itemRepository.findByIdAndIsDeleted(id, false);

        if(!isExistingGood.isPresent()){
            throw new ItemNotFoundException();
        }

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(goodsCreateRequestDto.getUnitId());

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId((long) goodsCreateRequestDto.getCategory());

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setId(id);
        goodsEntity.setName(goodsCreateRequestDto.getGoodName());
        goodsEntity.setDescription(goodsCreateRequestDto.getDescription());
        goodsEntity.setUnitsEntity(unitsEntity);
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setMinQuantity(goodsCreateRequestDto.getMinQuantity());
        goodsEntity.setDate(stringDateToLocalDateTime(goodsCreateRequestDto.getDate()));
        GoodsEntity response = itemRepository.saveAndFlush(goodsEntity);

        if(response.getId() != null){
            responseWrapperDto = new ResponseWrapperDto<>(true, "Update Successful", response);
        }else {
            responseWrapperDto = new ResponseWrapperDto<>(false, "Update Failed", response);
        }
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto<GoodsEntity> deleteItem(long id) {
        ResponseWrapperDto<GoodsEntity> responseWrapperDto;
        Optional<GoodsEntity> isExistingGood = itemRepository.findByIdAndIsDeleted(id, false);

        if(!isExistingGood.isPresent()){
            throw new ItemNotFoundException();
        }

        isExistingGood.get().setDeleted(true);
        GoodsEntity response = itemRepository.saveAndFlush(isExistingGood.get());
        responseWrapperDto = new ResponseWrapperDto<>(true, "Delete Successful", response);
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto<Optional<GoodsEntity>> getItem(long id) {
        ResponseWrapperDto<Optional<GoodsEntity>> responseWrapperDto = null;
        Optional<GoodsEntity> isExisting = itemRepository.findByIdAndIsDeleted(id, false);
        if(!isExisting.isPresent()){
            throw new ItemNotFoundException();
        }
        responseWrapperDto = new ResponseWrapperDto<>(true,"Get Item Successful" ,isExisting);
        return  responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto<InventoryPaginationDto> getAllItem(int categoryId, int size, int page) {
        ResponseWrapperDto<InventoryPaginationDto> responseWrapperDto;

        CategoryEntity itemCategory = new CategoryEntity();
        itemCategory.setId((long)categoryId);

        Pageable paging = PageRequest.of(page, size, Sort.by("name"));
        Page<GoodsEntityResponseDto> result = itemRepository.findGoodsEntities(itemCategory, false, paging);
        InventoryPaginationDto inventoryPaginationDto = new InventoryPaginationDto<List<GoodsEntity>>(
                result.getTotalElements(),
                result.getTotalPages(),
                size,
                page,
                new ArrayList<GoodsEntity>());

        if(result.hasContent()) {
            inventoryPaginationDto.setContent(result.getContent());
        }

        responseWrapperDto = new ResponseWrapperDto<>(true, "Get All Items Successful", inventoryPaginationDto);
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto<List<UnitsEntity>> getUnitList() {
        List<UnitsEntity> unitList = unitRepository.findAll();
        ResponseWrapperDto<List<UnitsEntity>> responseWrapperDto = new ResponseWrapperDto<>(true,"success",unitList);
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto<List<CategoryEntity>> getItemCategoryList() {
        List<CategoryEntity> categoryList = itemCategoryRepository.findAll();
        ResponseWrapperDto<List<CategoryEntity>> responseWrapperDto = new ResponseWrapperDto<>(true,"success",categoryList);
        return responseWrapperDto;
    }

    private LocalDate stringDateToLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
}
