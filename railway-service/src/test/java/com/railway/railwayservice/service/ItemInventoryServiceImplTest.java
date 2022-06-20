package com.railway.railwayservice.service;//package com.railway.railwayservice.service;

import com.railway.railwayservice.Exceptions.ItemCountDecrementException;
import com.railway.railwayservice.Exceptions.ItemNotFoundException;
import com.railway.railwayservice.Exceptions.ItemQuantityException;
import com.railway.railwayservice.Exceptions.RuntimeExceptionHere;
import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.dtos.InventoryPaginationDto;
import com.railway.railwayservice.dtos.InventoryResponseDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.InventoryEntity;
import com.railway.railwayservice.entity.UnitsEntity;
import com.railway.railwayservice.enums.InventoryFilter;
import com.railway.railwayservice.enums.InventoryType;
import com.railway.railwayservice.mappers.InventoryMapper;
import com.railway.railwayservice.repository.ItemInventoryRepository;
import com.railway.railwayservice.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ItemInventoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ItemInventoryServiceImplTest {
    @MockBean
    private ItemInventoryRepository itemInventoryRepository;

    @Autowired
    private ItemInventoryServiceImpl itemInventoryServiceImpl;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private InventoryMapper inventoryMapper;

    @Test
    void testGetItemLookup() throws RuntimeExceptionHere {
        when(this.itemRepository.findGoodsList((com.railway.railwayservice.entity.CategoryEntity) any(), anyBoolean()))
                .thenReturn(new ArrayList<>());
        ResponseWrapperDto actualItemLookup = this.itemInventoryServiceImpl.getItemLookup(1L);
        assertTrue(((Collection<Object>) actualItemLookup.getBody()).isEmpty());
        assertTrue(actualItemLookup.getStatus());
        assertEquals("Fetch Success.", actualItemLookup.getStatusMessage());
        verify(this.itemRepository).findGoodsList((com.railway.railwayservice.entity.CategoryEntity) any(), anyBoolean());
    }

    @Test
    void testGetItemLookup2() throws RuntimeExceptionHere {
        when(this.itemRepository.findGoodsList((com.railway.railwayservice.entity.CategoryEntity) any(), anyBoolean()))
                .thenThrow(new ItemNotFoundException(""));
        assertThrows(ItemNotFoundException.class, () -> this.itemInventoryServiceImpl.getItemLookup(1L));
        verify(this.itemRepository).findGoodsList((com.railway.railwayservice.entity.CategoryEntity) any(), anyBoolean());
    }

    @Test
    void testCreateInventory() throws ItemCountDecrementException, ItemNotFoundException, ItemQuantityException {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setIsDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);
        Optional<GoodsEntity> ofResult = Optional.of(goodsEntity);
        when(this.itemRepository.findById((Long) any())).thenReturn(ofResult);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(123L);
        categoryEntity1.setName("Name");

        UnitsEntity unitsEntity1 = new UnitsEntity();
        unitsEntity1.setId(123L);
        unitsEntity1.setName("Name");

        GoodsEntity goodsEntity1 = new GoodsEntity();
        goodsEntity1.setCategoryEntity(categoryEntity1);
        goodsEntity1.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity1.setIsDeleted(true);
        goodsEntity1.setDescription("The characteristics of someone or something");
        goodsEntity1.setId(123L);
        goodsEntity1.setInventoryEntity(new HashSet<>());
        goodsEntity1.setMinQuantity(10.0f);
        goodsEntity1.setName("Name");
        goodsEntity1.setTotalQuantity(10.0f);
        goodsEntity1.setUnitsEntity(unitsEntity1);

        UnitsEntity unitsEntity2 = new UnitsEntity();
        unitsEntity2.setId(123L);
        unitsEntity2.setName("Name");

        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setDate(LocalDate.ofEpochDay(1L));
        inventoryEntity.setDescription("The characteristics of someone or something");
        inventoryEntity.setGoodsEntity(goodsEntity1);
        inventoryEntity.setHandoverTo("alice.liddell@example.org");
        inventoryEntity.setId(123L);
        inventoryEntity.setInventoryType(InventoryType.GOODS_IN);
        inventoryEntity.setQuantity(10.0f);
        inventoryEntity.setShedStoreNo("Shed Store No");
        inventoryEntity.setSupervisorName("Supervisor Name");
        inventoryEntity.setTime(LocalTime.of(1, 1));
        inventoryEntity.setUnitsEntity(unitsEntity2);
        when(this.itemInventoryRepository.saveAndFlush((InventoryEntity) any())).thenReturn(inventoryEntity);

        CreateInventoryDto createInventoryDto = new CreateInventoryDto();
        createInventoryDto.setDate("2020-03-01");
        createInventoryDto.setDescription("The characteristics of someone or something");
        createInventoryDto.setGoodsId(123L);
        createInventoryDto.setHandoverTo("alice.liddell@example.org");
        createInventoryDto.setId(123L);
        createInventoryDto.setInventoryType(InventoryType.GOODS_IN);
        createInventoryDto.setQuantity(10.0f);
        createInventoryDto.setShedStoreNo("Shed Store No");
        createInventoryDto.setSupervisorName("Supervisor Name");
        createInventoryDto.setTime("Time");
        ResponseWrapperDto actualCreateInventoryResult = this.itemInventoryServiceImpl.createInventory(createInventoryDto);
        assertNull(actualCreateInventoryResult.getBody());
        assertTrue(actualCreateInventoryResult.getStatus());
        assertEquals("Create Success.", actualCreateInventoryResult.getStatusMessage());
        verify(this.itemRepository).findById((Long) any());
        verify(this.itemInventoryRepository).saveAndFlush((InventoryEntity) any());
    }

    @Test
    void testCreateInventory2() throws ItemCountDecrementException, ItemNotFoundException, ItemQuantityException {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setIsDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);
        Optional<GoodsEntity> ofResult = Optional.of(goodsEntity);
        when(this.itemRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.itemInventoryRepository.saveAndFlush((InventoryEntity) any())).thenThrow(new ItemNotFoundException(""));

        CreateInventoryDto createInventoryDto = new CreateInventoryDto();
        createInventoryDto.setDate("2020-03-01");
        createInventoryDto.setDescription("The characteristics of someone or something");
        createInventoryDto.setGoodsId(123L);
        createInventoryDto.setHandoverTo("alice.liddell@example.org");
        createInventoryDto.setId(123L);
        createInventoryDto.setInventoryType(InventoryType.GOODS_IN);
        createInventoryDto.setQuantity(10.0f);
        createInventoryDto.setShedStoreNo("Shed Store No");
        createInventoryDto.setSupervisorName("Supervisor Name");
        createInventoryDto.setTime("Time");
        assertThrows(ItemNotFoundException.class, () -> this.itemInventoryServiceImpl.createInventory(createInventoryDto));
        verify(this.itemRepository).findById((Long) any());
        verify(this.itemInventoryRepository).saveAndFlush((InventoryEntity) any());
    }

    @Test
    void testCreateInventory3() throws ItemCountDecrementException, ItemNotFoundException, ItemQuantityException {
        when(this.itemRepository.findById((Long) any())).thenReturn(Optional.empty());

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setIsDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);

        UnitsEntity unitsEntity1 = new UnitsEntity();
        unitsEntity1.setId(123L);
        unitsEntity1.setName("Name");

        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setDate(LocalDate.ofEpochDay(1L));
        inventoryEntity.setDescription("The characteristics of someone or something");
        inventoryEntity.setGoodsEntity(goodsEntity);
        inventoryEntity.setHandoverTo("alice.liddell@example.org");
        inventoryEntity.setId(123L);
        inventoryEntity.setInventoryType(InventoryType.GOODS_IN);
        inventoryEntity.setQuantity(10.0f);
        inventoryEntity.setShedStoreNo("Shed Store No");
        inventoryEntity.setSupervisorName("Supervisor Name");
        inventoryEntity.setTime(LocalTime.of(1, 1));
        inventoryEntity.setUnitsEntity(unitsEntity1);
        when(this.itemInventoryRepository.saveAndFlush((InventoryEntity) any())).thenReturn(inventoryEntity);

        CreateInventoryDto createInventoryDto = new CreateInventoryDto();
        createInventoryDto.setDate("2020-03-01");
        createInventoryDto.setDescription("The characteristics of someone or something");
        createInventoryDto.setGoodsId(123L);
        createInventoryDto.setHandoverTo("alice.liddell@example.org");
        createInventoryDto.setId(123L);
        createInventoryDto.setInventoryType(InventoryType.GOODS_IN);
        createInventoryDto.setQuantity(10.0f);
        createInventoryDto.setShedStoreNo("Shed Store No");
        createInventoryDto.setSupervisorName("Supervisor Name");
        createInventoryDto.setTime("Time");
        assertThrows(ItemNotFoundException.class, () -> this.itemInventoryServiceImpl.createInventory(createInventoryDto));
        verify(this.itemRepository).findById((Long) any());
    }

    @Test
    void testCreateInventory4() throws ItemCountDecrementException, ItemNotFoundException, ItemQuantityException {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(123L);
        categoryEntity.setName("Name");

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(123L);
        unitsEntity.setName("Name");

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity.setIsDeleted(true);
        goodsEntity.setDescription("The characteristics of someone or something");
        goodsEntity.setId(123L);
        goodsEntity.setInventoryEntity(new HashSet<>());
        goodsEntity.setMinQuantity(10.0f);
        goodsEntity.setName("Name");
        goodsEntity.setTotalQuantity(10.0f);
        goodsEntity.setUnitsEntity(unitsEntity);
        Optional<GoodsEntity> ofResult = Optional.of(goodsEntity);
        when(this.itemRepository.findById((Long) any())).thenReturn(ofResult);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(123L);
        categoryEntity1.setName("Name");

        UnitsEntity unitsEntity1 = new UnitsEntity();
        unitsEntity1.setId(123L);
        unitsEntity1.setName("Name");

        GoodsEntity goodsEntity1 = new GoodsEntity();
        goodsEntity1.setCategoryEntity(categoryEntity1);
        goodsEntity1.setDate(LocalDate.ofEpochDay(1L));
        goodsEntity1.setIsDeleted(true);
        goodsEntity1.setDescription("The characteristics of someone or something");
        goodsEntity1.setId(123L);
        goodsEntity1.setInventoryEntity(new HashSet<>());
        goodsEntity1.setMinQuantity(10.0f);
        goodsEntity1.setName("Name");
        goodsEntity1.setTotalQuantity(10.0f);
        goodsEntity1.setUnitsEntity(unitsEntity1);

        UnitsEntity unitsEntity2 = new UnitsEntity();
        unitsEntity2.setId(123L);
        unitsEntity2.setName("Name");

        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setDate(LocalDate.ofEpochDay(1L));
        inventoryEntity.setDescription("The characteristics of someone or something");
        inventoryEntity.setGoodsEntity(goodsEntity1);
        inventoryEntity.setHandoverTo("alice.liddell@example.org");
        inventoryEntity.setId(123L);
        inventoryEntity.setInventoryType(InventoryType.GOODS_IN);
        inventoryEntity.setQuantity(10.0f);
        inventoryEntity.setShedStoreNo("Shed Store No");
        inventoryEntity.setSupervisorName("Supervisor Name");
        inventoryEntity.setTime(LocalTime.of(1, 1));
        inventoryEntity.setUnitsEntity(unitsEntity2);
        when(this.itemInventoryRepository.saveAndFlush((InventoryEntity) any())).thenReturn(inventoryEntity);
        CreateInventoryDto createInventoryDto = mock(CreateInventoryDto.class);
        when(createInventoryDto.getQuantity()).thenThrow(new ItemCountDecrementException());
        when(createInventoryDto.getDescription()).thenThrow(new ItemCountDecrementException());
        when(createInventoryDto.getHandoverTo()).thenThrow(new ItemCountDecrementException());
        when(createInventoryDto.getShedStoreNo()).thenThrow(new ItemCountDecrementException());
        when(createInventoryDto.getSupervisorName()).thenThrow(new ItemCountDecrementException());
        when(createInventoryDto.getInventoryType()).thenReturn(InventoryType.GOODS_IN);
        when(createInventoryDto.getGoodsId()).thenReturn(123L);
        doNothing().when(createInventoryDto).setDate((String) any());
        doNothing().when(createInventoryDto).setDescription((String) any());
        doNothing().when(createInventoryDto).setGoodsId((Long) any());
        doNothing().when(createInventoryDto).setHandoverTo((String) any());
        doNothing().when(createInventoryDto).setId((Long) any());
        doNothing().when(createInventoryDto).setInventoryType((InventoryType) any());
        doNothing().when(createInventoryDto).setQuantity(anyFloat());
        doNothing().when(createInventoryDto).setShedStoreNo((String) any());
        doNothing().when(createInventoryDto).setSupervisorName((String) any());
        doNothing().when(createInventoryDto).setTime((String) any());
        createInventoryDto.setDate("2020-03-01");
        createInventoryDto.setDescription("The characteristics of someone or something");
        createInventoryDto.setGoodsId(123L);
        createInventoryDto.setHandoverTo("alice.liddell@example.org");
        createInventoryDto.setId(123L);
        createInventoryDto.setInventoryType(InventoryType.GOODS_IN);
        createInventoryDto.setQuantity(10.0f);
        createInventoryDto.setShedStoreNo("Shed Store No");
        createInventoryDto.setSupervisorName("Supervisor Name");
        createInventoryDto.setTime("Time");
        assertThrows(ItemCountDecrementException.class,
                () -> this.itemInventoryServiceImpl.createInventory(createInventoryDto));
        verify(this.itemRepository).findById((Long) any());
        verify(createInventoryDto).getInventoryType();
        verify(createInventoryDto).getQuantity();
        verify(createInventoryDto).getGoodsId();
        verify(createInventoryDto).setDate((String) any());
        verify(createInventoryDto).setDescription((String) any());
        verify(createInventoryDto).setGoodsId((Long) any());
        verify(createInventoryDto).setHandoverTo((String) any());
        verify(createInventoryDto).setId((Long) any());
        verify(createInventoryDto).setInventoryType((InventoryType) any());
        verify(createInventoryDto).setQuantity(anyFloat());
        verify(createInventoryDto).setShedStoreNo((String) any());
        verify(createInventoryDto).setSupervisorName((String) any());
        verify(createInventoryDto).setTime((String) any());
    }

    @Test
    void testGetAllInventory() throws RuntimeExceptionHere {
        ArrayList<InventoryResponseDto> inventoryResponseDtoList = new ArrayList<>();
        when(this.itemInventoryRepository.findAllByInventory((Long) any(), anyBoolean(), (java.util.List<String>) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(inventoryResponseDtoList));
        ResponseWrapperDto actualAllInventory = this.itemInventoryServiceImpl.getAllInventory(123L, InventoryFilter.IN, 1,
                3);
        assertEquals(
                "ResponseWrapperDto(status=true, statusMessage=Fetch Success., body=InventoryPaginationDto(totalCount=0,"
                        + " totalPage=1, pageSize=3, page=1, content=[]))",
                actualAllInventory.toString());
        assertTrue(actualAllInventory.getStatus());
        assertEquals("Fetch Success.", actualAllInventory.getStatusMessage());
        assertEquals(1, ((InventoryPaginationDto) actualAllInventory.getBody()).getTotalPage());
        assertEquals(0L, ((InventoryPaginationDto) actualAllInventory.getBody()).getTotalCount());
        assertEquals(3, ((InventoryPaginationDto) actualAllInventory.getBody()).getPageSize());
        assertEquals(1, ((InventoryPaginationDto) actualAllInventory.getBody()).getPage());
        assertEquals(inventoryResponseDtoList, ((InventoryPaginationDto) actualAllInventory.getBody()).getContent());
        verify(this.itemInventoryRepository).findAllByInventory((Long) any(), anyBoolean(), (java.util.List<String>) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetAllInventory2() throws RuntimeExceptionHere {
        ArrayList<InventoryResponseDto> inventoryResponseDtoList = new ArrayList<>();
        when(this.itemInventoryRepository.findAllByInventory((Long) any(), anyBoolean(), (java.util.List<String>) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(inventoryResponseDtoList));
        ResponseWrapperDto actualAllInventory = this.itemInventoryServiceImpl.getAllInventory(123L, InventoryFilter.OUT, 1,
                3);
        assertEquals(
                "ResponseWrapperDto(status=true, statusMessage=Fetch Success., body=InventoryPaginationDto(totalCount=0,"
                        + " totalPage=1, pageSize=3, page=1, content=[]))",
                actualAllInventory.toString());
        assertTrue(actualAllInventory.getStatus());
        assertEquals("Fetch Success.", actualAllInventory.getStatusMessage());
        assertEquals(1, ((InventoryPaginationDto) actualAllInventory.getBody()).getTotalPage());
        assertEquals(0L, ((InventoryPaginationDto) actualAllInventory.getBody()).getTotalCount());
        assertEquals(3, ((InventoryPaginationDto) actualAllInventory.getBody()).getPageSize());
        assertEquals(1, ((InventoryPaginationDto) actualAllInventory.getBody()).getPage());
        assertEquals(inventoryResponseDtoList, ((InventoryPaginationDto) actualAllInventory.getBody()).getContent());
        verify(this.itemInventoryRepository).findAllByInventory((Long) any(), anyBoolean(), (java.util.List<String>) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetAllInventory3() throws RuntimeExceptionHere {
        ArrayList<InventoryResponseDto> inventoryResponseDtoList = new ArrayList<>();
        when(this.itemInventoryRepository.findAllByInventory((Long) any(), anyBoolean(), (java.util.List<String>) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(inventoryResponseDtoList));
        ResponseWrapperDto actualAllInventory = this.itemInventoryServiceImpl.getAllInventory(123L, InventoryFilter.ALL, 1,
                3);
        assertEquals(
                "ResponseWrapperDto(status=true, statusMessage=Fetch Success., body=InventoryPaginationDto(totalCount=0,"
                        + " totalPage=1, pageSize=3, page=1, content=[]))",
                actualAllInventory.toString());
        assertTrue(actualAllInventory.getStatus());
        assertEquals("Fetch Success.", actualAllInventory.getStatusMessage());
        assertEquals(1, ((InventoryPaginationDto) actualAllInventory.getBody()).getTotalPage());
        assertEquals(0L, ((InventoryPaginationDto) actualAllInventory.getBody()).getTotalCount());
        assertEquals(3, ((InventoryPaginationDto) actualAllInventory.getBody()).getPageSize());
        assertEquals(1, ((InventoryPaginationDto) actualAllInventory.getBody()).getPage());
        assertEquals(inventoryResponseDtoList, ((InventoryPaginationDto) actualAllInventory.getBody()).getContent());
        verify(this.itemInventoryRepository).findAllByInventory((Long) any(), anyBoolean(), (java.util.List<String>) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetAllInventory4() throws RuntimeExceptionHere {
        when(this.itemInventoryRepository.findAllByInventory((Long) any(), anyBoolean(), (java.util.List<String>) any(),
                (org.springframework.data.domain.Pageable) any())).thenThrow(new ItemNotFoundException(""));
        assertThrows(ItemNotFoundException.class,
                () -> this.itemInventoryServiceImpl.getAllInventory(123L, InventoryFilter.IN, 1, 3));
        verify(this.itemInventoryRepository).findAllByInventory((Long) any(), anyBoolean(), (java.util.List<String>) any(),
                (org.springframework.data.domain.Pageable) any());
    }
}

