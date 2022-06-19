package com.railway.railwayservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.railway.railwayservice.Exceptions.ItemAllReadyExistingException;
import com.railway.railwayservice.Exceptions.ItemNotFoundException;
import com.railway.railwayservice.Exceptions.RuntimeExceptionHere;
import com.railway.railwayservice.controllers.ItemInventoryController;
import com.railway.railwayservice.dtos.GoodsCreateRequestDto;
import com.railway.railwayservice.dtos.GoodsEntityResponseDto;
import com.railway.railwayservice.dtos.InventoryPaginationDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.InventoryEntity;
import com.railway.railwayservice.entity.UnitsEntity;
import com.railway.railwayservice.enums.ActiveStatus;
import com.railway.railwayservice.mappers.GoodsMapper;
import com.railway.railwayservice.repository.ItemCategoryRepository;
import com.railway.railwayservice.repository.ItemRepository;
import com.railway.railwayservice.repository.UnitRepository;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * The type Item service impl test.
 */
@ContextConfiguration(classes = {ItemServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ItemServiceImplTest {
    @MockBean
    private ItemCategoryRepository itemCategoryRepository;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private UnitRepository unitRepository;

    @MockBean
    private GoodsMapper goodsMapper;

    private ItemServiceImpl itemServiceImpl;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        itemServiceImpl = new ItemServiceImpl(itemRepository,unitRepository,itemCategoryRepository,goodsMapper);
    }

    /**
     * Tear down.
     */
    @AfterEach
    void tearDown() {
    }

    /**
     * Create item when id not null.
     */
    @Test
    void createItemWhenIdNotNull(){
        RuntimeExceptionHere actualResult = assertThrows(RuntimeExceptionHere.class, () -> {
            GoodsCreateRequestDto goodsCreateRequestDto = new GoodsCreateRequestDto();
            goodsCreateRequestDto.setId(1L);
            this.itemServiceImpl.createItem(goodsCreateRequestDto);
        });

        String message = "id should be null.";
        assertEquals(message,actualResult.getMessage());
        verify(this.itemRepository,never()).findByNameAndIsDeleted(anyString(), anyBoolean());
    }

    /**
     * Create item when name null.
     */
    @Test
    void createItemWhenNameNull(){
        RuntimeExceptionHere actualResult = assertThrows(RuntimeExceptionHere.class, () -> {
            GoodsCreateRequestDto goodsCreateRequestDto = new GoodsCreateRequestDto();
            goodsCreateRequestDto.setGoodName(null);
            this.itemServiceImpl.createItem(goodsCreateRequestDto);
        });

        String message = "Goods Name can not be null or empty";
        assertEquals(message,actualResult.getMessage());
        verify(this.itemRepository,never()).findByNameAndIsDeleted(anyString(), anyBoolean());
    }

    /**
     * Create item when item already exist.
     */
    @Test
    void createItemWhenItemAlreadyExist(){
        when(this.itemRepository.findByNameAndIsDeleted(anyString(),anyBoolean())).thenReturn(Optional.of(new GoodsEntity()));

        ItemAllReadyExistingException actualResult = assertThrows(ItemAllReadyExistingException.class, () -> {
            GoodsCreateRequestDto goodsCreateRequestDto = new GoodsCreateRequestDto();
            goodsCreateRequestDto.setGoodName("goods");
            this.itemServiceImpl.createItem(goodsCreateRequestDto);
        });

        String message = "Item Already Available";
        assertEquals(message,actualResult.getMessage());
        verify(this.itemRepository).findByNameAndIsDeleted(anyString(), anyBoolean());
        verify(this.goodsMapper,never()).toEntity(any());
    }

    /**
     * Create item.
     */
    @Test
    void createItem(){
        when(this.itemRepository.findByNameAndIsDeleted(anyString(),anyBoolean())).thenReturn(Optional.empty());
        when(this.goodsMapper.toEntity(any())).thenReturn(new GoodsEntity());
        when(this.goodsMapper.toDto(any())).thenReturn(new GoodsCreateRequestDto());
        when(this.itemRepository.saveAndFlush(any())).thenReturn(new GoodsEntity());

        ResponseWrapperDto<GoodsCreateRequestDto> actualResult = this.itemServiceImpl.createItem(getGoodsCreateDto());

        assertEquals(ActiveStatus.ACTIVE.getValue(),actualResult.getStatus());
        verify(this.itemRepository).findByNameAndIsDeleted(anyString(), anyBoolean());
        verify(this.goodsMapper).toEntity(any());
        verify(this.itemRepository).saveAndFlush(any());
    }

    /**
     * Update item when id null.
     */
    @Test
    void updateItemWhenIdNull(){
        RuntimeExceptionHere actualResult = assertThrows(RuntimeExceptionHere.class, () -> {
            this.itemServiceImpl.updateItem(getGoodsCreateDto(),1L);
        });

        String message = "Id can not be null.";
        assertEquals(message,actualResult.getMessage());
        verify(this.itemRepository,never()).findByNameAndIsDeleted(anyString(), anyBoolean());
    }

    /**
     * Update item when id mis match.
     */
    @Test
    void updateItemWhenIdMisMatch(){
        RuntimeExceptionHere actualResult = assertThrows(RuntimeExceptionHere.class, () -> {
            GoodsCreateRequestDto goodsCreateDto = getGoodsCreateDto();
            goodsCreateDto.setId(123L);
            this.itemServiceImpl.updateItem(goodsCreateDto,1L);
        });

        String message = "Id mismatch error.";
        assertEquals(message,actualResult.getMessage());
        verify(this.itemRepository,never()).findByNameAndIsDeleted(anyString(), anyBoolean());
    }

    /**
     * Update item not exist.
     */
    @Test
    void updateItemNotExist(){
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(),anyBoolean())).thenReturn(Optional.empty());

        ItemNotFoundException actualResult = assertThrows(ItemNotFoundException.class, () -> {
            GoodsCreateRequestDto goodsCreateDto = getGoodsCreateDto();
            goodsCreateDto.setId(123L);
            this.itemServiceImpl.updateItem(goodsCreateDto,123L);
        });

        String message = "Item Not Found";
        assertEquals(message,actualResult.getMessage());
        verify(this.itemRepository).findByIdAndIsDeleted(anyLong(), anyBoolean());
        verify(this.goodsMapper,never()).toEntity(any());
    }

    /**
     * Update item.
     */
    @Test
    void updateItem(){
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(),anyBoolean())).thenReturn(Optional.of(new GoodsEntity()));
        when(this.goodsMapper.toEntity(any())).thenReturn(new GoodsEntity());
        when(this.goodsMapper.toDto(any())).thenReturn(new GoodsCreateRequestDto());
        when(this.itemRepository.saveAndFlush(any())).thenReturn(new GoodsEntity());

        GoodsCreateRequestDto goodsCreateDto = getGoodsCreateDto();
        goodsCreateDto.setId(123L);
        ResponseWrapperDto<GoodsEntity> actualResult = this.itemServiceImpl.updateItem(goodsCreateDto, 123L);

        assertEquals(ActiveStatus.ACTIVE.getValue(),actualResult.getStatus());
        verify(this.itemRepository).findByIdAndIsDeleted(anyLong(), anyBoolean());
        verify(this.goodsMapper).toEntity(any());
        verify(this.itemRepository).saveAndFlush(any());
    }

    /**
     * Delete item when item not exist.
     */
    @Test
    void deleteItemWhenItemNotExist(){
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(),anyBoolean())).thenReturn(Optional.empty());

        ItemNotFoundException actualResult = assertThrows(ItemNotFoundException.class, () -> {
            this.itemServiceImpl.deleteItem(123L);
        });

        String message = "Item Not Found";
        assertEquals(message,actualResult.getMessage());
        verify(this.itemRepository,never()).saveAndFlush(any());
    }

    /**
     * Delete item.
     */
    @Test
    void deleteItem(){
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(),anyBoolean())).thenReturn(Optional.of(new GoodsEntity()));
        ResponseWrapperDto<GoodsEntity> actualResult = this.itemServiceImpl.deleteItem(123L);
        assertEquals(ActiveStatus.ACTIVE.getValue(),actualResult.getStatus());
        verify(this.itemRepository).saveAndFlush(any());
    }

    /**
     * Get item when item not exist.
     */
    @Test
    void getItemWhenItemNotExist(){
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(),anyBoolean())).thenReturn(Optional.empty());

        ItemNotFoundException actualResult = assertThrows(ItemNotFoundException.class, () -> {
            this.itemServiceImpl.getItem(123L);
        });

        String message = "Item Not Found";
        assertEquals(message,actualResult.getMessage());
        verify(this.itemRepository).findByIdAndIsDeleted(anyLong(),anyBoolean());
    }

    /**
     * Get item.
     */
    @Test
    void getItem(){
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(),anyBoolean())).thenReturn(Optional.of(new GoodsEntity()));
        ResponseWrapperDto<GoodsEntity> actualResult = this.itemServiceImpl.getItem(123L);
        assertEquals(ActiveStatus.ACTIVE.getValue(),actualResult.getStatus());
        verify(this.itemRepository).findByIdAndIsDeleted(anyLong(),anyBoolean());
    }

    /*@Test
    void getAllItem(){
        List<GoodsEntityResponseDto> goodsEntityResponseDtos = new ArrayList<>();
        Page<GoodsEntityResponseDto> page= new PageImpl<>(Collections.EMPTY_LIST);
        when(this.itemRepository.findGoodsEntities(any(),any(),any())).thenReturn(page);
        ResponseWrapperDto<InventoryPaginationDto<List<GoodsEntityResponseDto>>> actualResult = this.itemServiceImpl.getAllItem(123, 10, 0);
        assertEquals(ActiveStatus.ACTIVE.getValue(),actualResult.getStatus());
        verify(this.itemRepository).findGoodsEntities(any(),any(),any());
    }*/

    private GoodsCreateRequestDto getGoodsCreateDto(){
        GoodsCreateRequestDto goodsCreateRequestDto = new GoodsCreateRequestDto();
        goodsCreateRequestDto.setGoodName("goods");
        goodsCreateRequestDto.setCategory(1);
        goodsCreateRequestDto.setUnitId(1L);
        goodsCreateRequestDto.setDate("2021-02-12");
        return goodsCreateRequestDto;
    }

    /**
     * Test get unit list.
     */
    @Test
    void testGetUnitList() {
        when(this.unitRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseWrapperDto<List<UnitsEntity>> actualUnitList = this.itemServiceImpl.getUnitList();
        assertEquals(ActiveStatus.ACTIVE.getValue(), actualUnitList.getStatus());
        verify(this.unitRepository).findAll();
    }

    /**
     * Test get unit list 2.
     */
    @Test
    void testGetUnitList2() {
        when(this.unitRepository.findAll()).thenThrow(new ItemAllReadyExistingException());
        assertThrows(ItemAllReadyExistingException.class, () -> this.itemServiceImpl.getUnitList());
        verify(this.unitRepository).findAll();
    }

    /**
     * Test get item category list.
     */
    @Test
    void testGetItemCategoryList() {
        when(this.itemCategoryRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseWrapperDto<List<CategoryEntity>> actualItemCategoryList = this.itemServiceImpl.getItemCategoryList();
        assertEquals(ActiveStatus.ACTIVE.getValue(), actualItemCategoryList.getStatus());
        verify(this.itemCategoryRepository).findAll();
    }

    /**
     * Test get item category list 2.
     */
    @Test
    void testGetItemCategoryList2() {
        when(this.itemCategoryRepository.findAll()).thenThrow(new ItemAllReadyExistingException());
        assertThrows(ItemAllReadyExistingException.class, () -> this.itemServiceImpl.getItemCategoryList());
        verify(this.itemCategoryRepository).findAll();
    }
}

