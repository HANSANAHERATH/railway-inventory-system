package com.railway.railwayservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.railway.railwayservice.Exceptions.ItemAllReadyExistingException;
import com.railway.railwayservice.Exceptions.ItemNotFoundException;
import com.railway.railwayservice.Exceptions.RuntimeExceptionHere;
import com.railway.railwayservice.dtos.GoodsCreateRequestDto;
import com.railway.railwayservice.dtos.GoodsEntityResponseDto;
import com.railway.railwayservice.dtos.InventoryPaginationDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.InventoryEntity;
import com.railway.railwayservice.entity.UnitsEntity;
import com.railway.railwayservice.repository.ItemCategoryRepository;
import com.railway.railwayservice.repository.ItemRepository;
import com.railway.railwayservice.repository.UnitRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ItemServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ItemServiceImplTest {
    @MockBean
    private ItemCategoryRepository itemCategoryRepository;

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private ItemServiceImpl itemServiceImpl;

    @MockBean
    private UnitRepository unitRepository;

    /*@Test
    void testCreateItem() throws RuntimeExceptionHere {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(123L);
        categoryEntity1.setName("Name");

        UnitsEntity unitsEntity1 = new UnitsEntity();
        unitsEntity1.setId(123L);
        unitsEntity1.setName("Name");

        GoodsEntity goodsEntity1 = new GoodsEntity();
        goodsEntity1.setCategoryEntity(categoryEntity1);
        goodsEntity1.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity1.setDeleted(true);
        goodsEntity1.setDescription("The characteristics of someone or something");
        goodsEntity1.setId(123L);
        goodsEntity1.setInventoryEntity(new HashSet<>());
        goodsEntity1.setMinQuantity(10.0f);
        goodsEntity1.setName("Name");
        goodsEntity1.setTotalQuantity(10.0f);
        goodsEntity1.setUnitsEntity(unitsEntity1);
        Optional<GoodsEntity> ofResult = Optional.of(goodsEntity1);
        when(this.itemRepository.saveAndFlush((GoodsEntity) any())).thenReturn(new GoodsEntity());
        when(this.itemRepository.findByNameAndIsDeleted((String) any(), anyBoolean())).thenReturn(ofResult);
        assertThrows(RuntimeExceptionHere.class, () -> this.itemServiceImpl.createItem(new GoodsCreateRequestDto(123L,
                "Good Name", "The characteristics of someone or something", 123L, "2020-03-01", "42", 1, 10.0f)));
        verify(this.itemRepository).findByNameAndIsDeleted((String) any(), anyBoolean());

    }*/

    @Test
    void testCreateItem2() throws RuntimeExceptionHere {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);
        when(this.itemRepository.findByNameAndIsDeleted((String) any(), anyBoolean())).thenReturn(Optional.empty());
        when(this.itemRepository.saveAndFlush((GoodsEntity) any())).thenReturn(goodsEntity);
        ResponseWrapperDto actualCreateItemResult = this.itemServiceImpl.createItem(new GoodsCreateRequestDto(123L,
                "Good Name", "The characteristics of someone or something", 123L, "2020-03-01", "42", 1, 10.0f));
        assertNull(actualCreateItemResult.getBody());
        assertTrue(actualCreateItemResult.isStatus());
        assertEquals("Saved Successful", actualCreateItemResult.getStatusMessage());
        verify(this.itemRepository).saveAndFlush((GoodsEntity) any());
        verify(this.itemRepository).findByNameAndIsDeleted((String) any(), anyBoolean());
        ResponseWrapperDto expectedUnitList = this.itemServiceImpl.getItemCategoryList();
        assertEquals(expectedUnitList, this.itemServiceImpl.getUnitList());
    }

   /* @Test
    void testCreateItem3() throws RuntimeExceptionHere {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(123L);
        categoryEntity1.setName("Name");

        UnitsEntity unitsEntity1 = new UnitsEntity();
        unitsEntity1.setId(123L);
        unitsEntity1.setName("Name");

        GoodsEntity goodsEntity1 = new GoodsEntity();
        goodsEntity1.setCategoryEntity(categoryEntity1);
        goodsEntity1.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity1.setDeleted(true);
        goodsEntity1.setDescription("The characteristics of someone or something");
        goodsEntity1.setId(123L);
        goodsEntity1.setInventoryEntity(new HashSet<>());
        goodsEntity1.setMinQuantity(10.0f);
        goodsEntity1.setName("Name");
        goodsEntity1.setTotalQuantity(10.0f);
        goodsEntity1.setUnitsEntity(unitsEntity1);
        Optional<GoodsEntity> ofResult = Optional.of(goodsEntity1);
        when(this.itemRepository.saveAndFlush((GoodsEntity) any())).thenReturn(goodsEntity);
        when(this.itemRepository.findByNameAndIsDeleted((String) any(), anyBoolean())).thenReturn(ofResult);
        assertThrows(RuntimeExceptionHere.class, () -> this.itemServiceImpl.createItem(null));
    }*/

    /*@Test
    void testCreateItem4() throws RuntimeExceptionHere {
        when(this.itemRepository.saveAndFlush((GoodsEntity) any())).thenThrow(new ItemNotFoundException());
        when(this.itemRepository.findByNameAndIsDeleted((String) any(), anyBoolean()))
                .thenThrow(new ItemNotFoundException());
        assertThrows(RuntimeExceptionHere.class, () -> this.itemServiceImpl.createItem(new GoodsCreateRequestDto(123L,
                "Good Name", "The characteristics of someone or something", 123L, "2020-03-01", "42", 1, 10.0f)));
        verify(this.itemRepository).findByNameAndIsDeleted((String) any(), anyBoolean());
    }*/

    @Test
    void testUpdateItem() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);
        Optional<GoodsEntity> ofResult = Optional.of(goodsEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(123L);
        categoryEntity1.setName("Name");

        UnitsEntity unitsEntity1 = new UnitsEntity();
        unitsEntity1.setId(123L);
        unitsEntity1.setName("Name");

        GoodsEntity goodsEntity1 = new GoodsEntity();
        goodsEntity1.setCategoryEntity(categoryEntity1);
        goodsEntity1.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity1.setDeleted(true);
        goodsEntity1.setDescription("The characteristics of someone or something");
        goodsEntity1.setId(123L);
        goodsEntity1.setInventoryEntity(new HashSet<>());
        goodsEntity1.setMinQuantity(10.0f);
        goodsEntity1.setName("Name");
        goodsEntity1.setTotalQuantity(10.0f);
        goodsEntity1.setUnitsEntity(unitsEntity1);
        when(this.itemRepository.saveAndFlush((GoodsEntity) any())).thenReturn(goodsEntity1);
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(), anyBoolean())).thenReturn(ofResult);
        ResponseWrapperDto actualUpdateItemResult = this.itemServiceImpl.updateItem(new GoodsCreateRequestDto(123L,
                "Good Name", "The characteristics of someone or something", 123L, "2020-03-01", "42", 1, 10.0f), 123L);
        assertEquals("ResponseWrapperDto(status=true, statusMessage=Update Successful, body=GoodsEntity(id=123, name=Name,"
                + " description=The characteristics of someone or something, date=1970-01-02, isDeleted=true, minQuantity=10.0,"
                + " totalQuantity=10.0, unitsEntity=UnitsEntity(id=123, name=Name), categoryEntity=CategoryEntity(id=123,"
                + " name=Name), inventoryEntity=[]))", actualUpdateItemResult.toString());
        assertTrue(actualUpdateItemResult.isStatus());
        assertEquals("Update Successful", actualUpdateItemResult.getStatusMessage());
        verify(this.itemRepository).saveAndFlush((GoodsEntity) any());
        verify(this.itemRepository).findByIdAndIsDeleted(anyLong(), anyBoolean());
        ResponseWrapperDto expectedUnitList = this.itemServiceImpl.getItemCategoryList();
        assertEquals(expectedUnitList, this.itemServiceImpl.getUnitList());
    }

    @Test
    void testUpdateItem2() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);
        Optional<GoodsEntity> ofResult = Optional.of(goodsEntity);
        when(this.itemRepository.saveAndFlush((GoodsEntity) any())).thenThrow(new ItemAllReadyExistingException());
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(), anyBoolean())).thenReturn(ofResult);
        assertThrows(ItemAllReadyExistingException.class,
                () -> this.itemServiceImpl.updateItem(new GoodsCreateRequestDto(123L, "Good Name",
                        "The characteristics of someone or something", 123L, "2020-03-01", "42", 1, 10.0f), 123L));
        verify(this.itemRepository).saveAndFlush((GoodsEntity) any());
        verify(this.itemRepository).findByIdAndIsDeleted(anyLong(), anyBoolean());
    }

    @Test
    void testUpdateItem3() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");

        GoodsEntity goodsEntity = mock(GoodsEntity.class);
        when(goodsEntity.getId()).thenReturn(123L);
        doNothing().when(goodsEntity).setCategoryEntity((CategoryEntity) any());
        doNothing().when(goodsEntity).setDate((LocalDate) any());
        doNothing().when(goodsEntity).setDeleted(anyBoolean());
        doNothing().when(goodsEntity).setDescription((String) any());
        doNothing().when(goodsEntity).setId((Long) any());
        doNothing().when(goodsEntity).setInventoryEntity((java.util.Set<InventoryEntity>) any());
        doNothing().when(goodsEntity).setMinQuantity(anyFloat());
        doNothing().when(goodsEntity).setName((String) any());
        doNothing().when(goodsEntity).setTotalQuantity(anyFloat());
        doNothing().when(goodsEntity).setUnitsEntity((UnitsEntity) any());
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);
        when(this.itemRepository.saveAndFlush((GoodsEntity) any())).thenReturn(goodsEntity);
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(), anyBoolean())).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> this.itemServiceImpl.updateItem(new GoodsCreateRequestDto(123L,
                "Good Name", "The characteristics of someone or something", 123L, "2020-03-01", "42", 1, 10.0f), 123L));
        verify(this.itemRepository).findByIdAndIsDeleted(anyLong(), anyBoolean());
        verify(goodsEntity).setCategoryEntity((CategoryEntity) any());
        verify(goodsEntity).setDate((LocalDate) any());
        verify(goodsEntity).setDeleted(anyBoolean());
        verify(goodsEntity).setDescription((String) any());
        verify(goodsEntity).setId((Long) any());
        verify(goodsEntity).setInventoryEntity((java.util.Set<InventoryEntity>) any());
        verify(goodsEntity).setMinQuantity(anyFloat());
        verify(goodsEntity).setName((String) any());
        verify(goodsEntity).setTotalQuantity(anyFloat());
        verify(goodsEntity).setUnitsEntity((UnitsEntity) any());
    }

    @Test
    void testDeleteItem() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);
        Optional<GoodsEntity> ofResult = Optional.of(goodsEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(123L);
        categoryEntity1.setName("Name");

        UnitsEntity unitsEntity1 = new UnitsEntity();
        unitsEntity1.setId(123L);
        unitsEntity1.setName("Name");

        GoodsEntity goodsEntity1 = new GoodsEntity();
        goodsEntity1.setCategoryEntity(categoryEntity1);
        goodsEntity1.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity1.setDeleted(true);
        goodsEntity1.setDescription("The characteristics of someone or something");
        goodsEntity1.setId(123L);
        goodsEntity1.setInventoryEntity(new HashSet<>());
        goodsEntity1.setMinQuantity(10.0f);
        goodsEntity1.setName("Name");
        goodsEntity1.setTotalQuantity(10.0f);
        goodsEntity1.setUnitsEntity(unitsEntity1);
        when(this.itemRepository.saveAndFlush((GoodsEntity) any())).thenReturn(goodsEntity1);
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(), anyBoolean())).thenReturn(ofResult);
        ResponseWrapperDto actualDeleteItemResult = this.itemServiceImpl.deleteItem(123L);
        assertEquals("ResponseWrapperDto(status=true, statusMessage=Delete Successful, body=GoodsEntity(id=123, name=Name,"
                + " description=The characteristics of someone or something, date=1970-01-02, isDeleted=true, minQuantity=10.0,"
                + " totalQuantity=10.0, unitsEntity=UnitsEntity(id=123, name=Name), categoryEntity=CategoryEntity(id=123,"
                + " name=Name), inventoryEntity=[]))", actualDeleteItemResult.toString());
        assertTrue(actualDeleteItemResult.isStatus());
        assertEquals("Delete Successful", actualDeleteItemResult.getStatusMessage());
        verify(this.itemRepository).saveAndFlush((GoodsEntity) any());
        verify(this.itemRepository).findByIdAndIsDeleted(anyLong(), anyBoolean());
        ResponseWrapperDto expectedUnitList = this.itemServiceImpl.getItemCategoryList();
        assertEquals(expectedUnitList, this.itemServiceImpl.getUnitList());
    }

    @Test
    void testDeleteItem2() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);
        Optional<GoodsEntity> ofResult = Optional.of(goodsEntity);
        when(this.itemRepository.saveAndFlush((GoodsEntity) any())).thenThrow(new ItemAllReadyExistingException());
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(), anyBoolean())).thenReturn(ofResult);
        assertThrows(ItemAllReadyExistingException.class, () -> this.itemServiceImpl.deleteItem(123L));
        verify(this.itemRepository).saveAndFlush((GoodsEntity) any());
        verify(this.itemRepository).findByIdAndIsDeleted(anyLong(), anyBoolean());
    }

    @Test
    void testDeleteItem3() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");
        GoodsEntity goodsEntity = mock(GoodsEntity.class);
        doNothing().when(goodsEntity).setCategoryEntity((CategoryEntity) any());
        doNothing().when(goodsEntity).setDate((LocalDate) any());
        doNothing().when(goodsEntity).setDeleted(anyBoolean());
        doNothing().when(goodsEntity).setDescription((String) any());
        doNothing().when(goodsEntity).setId((Long) any());
        doNothing().when(goodsEntity).setInventoryEntity((java.util.Set<InventoryEntity>) any());
        doNothing().when(goodsEntity).setMinQuantity(anyFloat());
        doNothing().when(goodsEntity).setName((String) any());
        doNothing().when(goodsEntity).setTotalQuantity(anyFloat());
        doNothing().when(goodsEntity).setUnitsEntity((UnitsEntity) any());
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);
        Optional<GoodsEntity> ofResult = Optional.of(goodsEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(123L);
        categoryEntity1.setName("Name");

        UnitsEntity unitsEntity1 = new UnitsEntity();
        unitsEntity1.setId(123L);
        unitsEntity1.setName("Name");

        GoodsEntity goodsEntity1 = new GoodsEntity();
        goodsEntity1.setCategoryEntity(categoryEntity1);
        goodsEntity1.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity1.setDeleted(true);
        goodsEntity1.setDescription("The characteristics of someone or something");
        goodsEntity1.setId(123L);
        goodsEntity1.setInventoryEntity(new HashSet<>());
        goodsEntity1.setMinQuantity(10.0f);
        goodsEntity1.setName("Name");
        goodsEntity1.setTotalQuantity(10.0f);
        goodsEntity1.setUnitsEntity(unitsEntity1);
        when(this.itemRepository.saveAndFlush((GoodsEntity) any())).thenReturn(goodsEntity1);
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(), anyBoolean())).thenReturn(ofResult);
        ResponseWrapperDto actualDeleteItemResult = this.itemServiceImpl.deleteItem(123L);
        assertEquals("ResponseWrapperDto(status=true, statusMessage=Delete Successful, body=GoodsEntity(id=123, name=Name,"
                + " description=The characteristics of someone or something, date=1970-01-02, isDeleted=true, minQuantity=10.0,"
                + " totalQuantity=10.0, unitsEntity=UnitsEntity(id=123, name=Name), categoryEntity=CategoryEntity(id=123,"
                + " name=Name), inventoryEntity=[]))", actualDeleteItemResult.toString());
        assertTrue(actualDeleteItemResult.isStatus());
        assertEquals("Delete Successful", actualDeleteItemResult.getStatusMessage());
        verify(this.itemRepository).saveAndFlush((GoodsEntity) any());
        verify(this.itemRepository).findByIdAndIsDeleted(anyLong(), anyBoolean());
        verify(goodsEntity).setCategoryEntity((CategoryEntity) any());
        verify(goodsEntity).setDate((LocalDate) any());
        verify(goodsEntity, atLeast(1)).setDeleted(anyBoolean());
        verify(goodsEntity).setDescription((String) any());
        verify(goodsEntity).setId((Long) any());
        verify(goodsEntity).setInventoryEntity((java.util.Set<InventoryEntity>) any());
        verify(goodsEntity).setMinQuantity(anyFloat());
        verify(goodsEntity).setName((String) any());
        verify(goodsEntity).setTotalQuantity(anyFloat());
        verify(goodsEntity).setUnitsEntity((UnitsEntity) any());
        ResponseWrapperDto expectedUnitList = this.itemServiceImpl.getItemCategoryList();
        assertEquals(expectedUnitList, this.itemServiceImpl.getUnitList());
    }

    @Test
    void testDeleteItem4() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);
        when(this.itemRepository.saveAndFlush((GoodsEntity) any())).thenReturn(goodsEntity);
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(), anyBoolean())).thenReturn(Optional.empty());

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(123L);
        categoryEntity1.setName("Name");

        UnitsEntity unitsEntity1 = new UnitsEntity();
        unitsEntity1.setId(123L);
        unitsEntity1.setName("Name");
        GoodsEntity goodsEntity1 = mock(GoodsEntity.class);
        doNothing().when(goodsEntity1).setCategoryEntity((CategoryEntity) any());
        doNothing().when(goodsEntity1).setDate((LocalDate) any());
        doNothing().when(goodsEntity1).setDeleted(anyBoolean());
        doNothing().when(goodsEntity1).setDescription((String) any());
        doNothing().when(goodsEntity1).setId((Long) any());
        doNothing().when(goodsEntity1).setInventoryEntity((java.util.Set<InventoryEntity>) any());
        doNothing().when(goodsEntity1).setMinQuantity(anyFloat());
        doNothing().when(goodsEntity1).setName((String) any());
        doNothing().when(goodsEntity1).setTotalQuantity(anyFloat());
        doNothing().when(goodsEntity1).setUnitsEntity((UnitsEntity) any());
        goodsEntity1.setCategoryEntity(categoryEntity1);
        goodsEntity1.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity1.setDeleted(true);
        goodsEntity1.setDescription("The characteristics of someone or something");
        goodsEntity1.setId(123L);
        goodsEntity1.setInventoryEntity(new HashSet<>());
        goodsEntity1.setMinQuantity(10.0f);
        goodsEntity1.setName("Name");
        goodsEntity1.setTotalQuantity(10.0f);
        goodsEntity1.setUnitsEntity(unitsEntity1);
        assertThrows(ItemNotFoundException.class, () -> this.itemServiceImpl.deleteItem(123L));
        verify(this.itemRepository).findByIdAndIsDeleted(anyLong(), anyBoolean());
        verify(goodsEntity1).setCategoryEntity((CategoryEntity) any());
        verify(goodsEntity1).setDate((LocalDate) any());
        verify(goodsEntity1).setDeleted(anyBoolean());
        verify(goodsEntity1).setDescription((String) any());
        verify(goodsEntity1).setId((Long) any());
        verify(goodsEntity1).setInventoryEntity((java.util.Set<InventoryEntity>) any());
        verify(goodsEntity1).setMinQuantity(anyFloat());
        verify(goodsEntity1).setName((String) any());
        verify(goodsEntity1).setTotalQuantity(anyFloat());
        verify(goodsEntity1).setUnitsEntity((UnitsEntity) any());
    }

    @Test
    void testGetItem() {
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(), anyBoolean())).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> this.itemServiceImpl.getItem(123L));
        verify(this.itemRepository).findByIdAndIsDeleted(anyLong(), anyBoolean());
    }

    @Test
    void testGetItem2() {
        when(this.itemRepository.findByIdAndIsDeleted(anyLong(), anyBoolean()))
                .thenThrow(new ItemAllReadyExistingException());
        assertThrows(ItemAllReadyExistingException.class, () -> this.itemServiceImpl.getItem(123L));
        verify(this.itemRepository).findByIdAndIsDeleted(anyLong(), anyBoolean());
    }

    @Test
    void testGetAllItem() {
        ArrayList<GoodsEntityResponseDto> goodsEntityResponseDtoList = new ArrayList<>();
        when(this.itemRepository.findGoodsEntities((CategoryEntity) any(), anyBoolean(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(goodsEntityResponseDtoList));
        ResponseWrapperDto actualAllItem = this.itemServiceImpl.getAllItem(123, 3, 1);
        assertEquals("ResponseWrapperDto(status=true, statusMessage=Get All Items Successful, body=InventoryPaginationDto"
                + "(totalCount=0, totalPage=1, pageSize=3, page=1, content=[]))", actualAllItem.toString());
        assertTrue(actualAllItem.isStatus());
        assertEquals("Get All Items Successful", actualAllItem.getStatusMessage());
        assertEquals(1, ((InventoryPaginationDto) actualAllItem.getBody()).getTotalPage());
        assertEquals(0L, ((InventoryPaginationDto) actualAllItem.getBody()).getTotalCount());
        assertEquals(3, ((InventoryPaginationDto) actualAllItem.getBody()).getPageSize());
        assertEquals(1, ((InventoryPaginationDto) actualAllItem.getBody()).getPage());
        assertEquals(goodsEntityResponseDtoList, ((InventoryPaginationDto) actualAllItem.getBody()).getContent());
        verify(this.itemRepository).findGoodsEntities((CategoryEntity) any(),
                anyBoolean(), (org.springframework.data.domain.Pageable) any());
        ResponseWrapperDto expectedUnitList = this.itemServiceImpl.getItemCategoryList();
        assertEquals(expectedUnitList, this.itemServiceImpl.getUnitList());
    }

    @Test
    void testGetAllItem2() {
        when(this.itemRepository.findGoodsEntities((CategoryEntity) any(), anyBoolean(),
                (org.springframework.data.domain.Pageable) any())).thenThrow(new ItemAllReadyExistingException());
        assertThrows(ItemAllReadyExistingException.class, () -> this.itemServiceImpl.getAllItem(123, 3, 1));
        verify(this.itemRepository).findGoodsEntities((CategoryEntity) any(),
                anyBoolean(), (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetUnitList() {
        when(this.unitRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseWrapperDto actualUnitList = this.itemServiceImpl.getUnitList();
        assertTrue(((Collection<Object>) actualUnitList.getBody()).isEmpty());
        assertTrue(actualUnitList.isStatus());
        assertEquals("success", actualUnitList.getStatusMessage());
        verify(this.unitRepository).findAll();
        assertEquals(actualUnitList, this.itemServiceImpl.getItemCategoryList());
    }

    @Test
    void testGetUnitList2() {
        when(this.unitRepository.findAll()).thenThrow(new ItemAllReadyExistingException());
        assertThrows(ItemAllReadyExistingException.class, () -> this.itemServiceImpl.getUnitList());
        verify(this.unitRepository).findAll();
    }

    @Test
    void testGetItemCategoryList() {
        when(this.itemCategoryRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseWrapperDto actualItemCategoryList = this.itemServiceImpl.getItemCategoryList();
        assertTrue(((Collection<Object>) actualItemCategoryList.getBody()).isEmpty());
        assertTrue(actualItemCategoryList.isStatus());
        assertEquals("success", actualItemCategoryList.getStatusMessage());
        verify(this.itemCategoryRepository).findAll();
        assertEquals(actualItemCategoryList, this.itemServiceImpl.getUnitList());
    }

    @Test
    void testGetItemCategoryList2() {
        when(this.itemCategoryRepository.findAll()).thenThrow(new ItemAllReadyExistingException());
        assertThrows(ItemAllReadyExistingException.class, () -> this.itemServiceImpl.getItemCategoryList());
        verify(this.itemCategoryRepository).findAll();
    }
}

