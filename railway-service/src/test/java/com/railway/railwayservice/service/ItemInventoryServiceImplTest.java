package com.railway.railwayservice.service;//package com.railway.railwayservice.service;

import com.railway.railwayservice.Exceptions.ItemCountDecrementException;
import com.railway.railwayservice.Exceptions.ItemNotFoundException;
import com.railway.railwayservice.Exceptions.ItemQuantityException;
import com.railway.railwayservice.Exceptions.RuntimeExceptionHere;
import com.railway.railwayservice.dtos.*;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.InventoryEntity;
import com.railway.railwayservice.entity.UnitsEntity;
import com.railway.railwayservice.enums.ActiveStatus;
import com.railway.railwayservice.enums.InventoryFilter;
import com.railway.railwayservice.enums.InventoryType;
import com.railway.railwayservice.mappers.InventoryMapper;
import com.railway.railwayservice.repository.ItemCategoryRepository;
import com.railway.railwayservice.repository.ItemInventoryRepository;
import com.railway.railwayservice.repository.ItemRepository;
import com.railway.railwayservice.service.impl.ItemInventoryServiceImpl;
import com.railway.railwayservice.service.impl.ItemServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ItemInventoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ItemInventoryServiceImplTest {
    @MockBean
    private ItemInventoryRepository itemInventoryRepository;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private InventoryMapper inventoryMapper;

    @MockBean
    private ItemCategoryRepository itemCategoryRepository;

    private ItemInventoryServiceImpl itemInventoryServiceImpl;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        itemInventoryServiceImpl = new ItemInventoryServiceImpl(itemRepository,itemInventoryRepository,inventoryMapper,itemCategoryRepository);
    }

    /**
     * Tear down.
     */
    @AfterEach
    void tearDown() {
    }

    @Test
    void getItemLookupWhenExistingCategoryNotFound(){
        when(this.itemCategoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeExceptionHere result = assertThrows(RuntimeExceptionHere.class, () -> {
            this.itemInventoryServiceImpl.getItemLookup(123L);
        });

        String message = "Can not found category.";
        assertEquals(message,result.getMessage());
        verify(this.itemCategoryRepository).findById(anyLong());
        verify(this.itemRepository, never()).findGoodsList(any(),anyBoolean());
    }

    @Test
    void getItemLookup(){
        when(this.itemCategoryRepository.findById(anyLong())).thenReturn(Optional.of(new CategoryEntity()));
        when(this.itemRepository.findGoodsList(any(),anyBoolean())).thenReturn(new ArrayList<>());

        ResponseWrapperDto<List<GoodNameLookupResponseDto>> result = this.itemInventoryServiceImpl.getItemLookup(123L);

        assertEquals(ActiveStatus.ACTIVE.getValue(), result.getStatus());
        verify(this.itemCategoryRepository).findById(anyLong());
        verify(this.itemRepository).findGoodsList(any(),anyBoolean());
    }


    @Test
    void createInventoryIfIdNotNull(){
        when(this.itemRepository.findById(anyLong())).thenReturn(Optional.empty());

        ItemNotFoundException result = assertThrows(ItemNotFoundException.class, () -> {
            CreateInventoryDto createInventoryDto = new CreateInventoryDto();
            createInventoryDto.setGoodsId(123L);
            this.itemInventoryServiceImpl.createInventory(createInventoryDto);
        });

        String message = "Can not find goods.";
        assertEquals(message,result.getMessage());
        verify(this.itemRepository).findById(anyLong());
        verify(this.inventoryMapper,never()).toEntity(any());
    }

    @Test
    void createInventoryIfExistingGoodEntityNotFound(){
        RuntimeExceptionHere result = assertThrows(RuntimeExceptionHere.class, () -> {
            CreateInventoryDto createInventoryDto = new CreateInventoryDto();
            createInventoryDto.setId(123L);
            this.itemInventoryServiceImpl.createInventory(createInventoryDto);
        });

        String message = "Id should be null.";
        assertEquals(message,result.getMessage());
        verify(this.itemCategoryRepository,never()).findById(anyLong());
    }

    @Test
    void createInventoryIfInventoryTypeIsGoodsIn(){
        GoodsEntity existingGoodsEntity = new GoodsEntity();
        existingGoodsEntity.setTotalQuantity(100F);
        existingGoodsEntity.setUnitsEntity(mockUnitEntity());

        when(this.itemRepository.findById(anyLong())).thenReturn(Optional.of(existingGoodsEntity));
        when(this.inventoryMapper.toEntity(any())).thenReturn(new InventoryEntity());
        when(this.itemInventoryRepository.saveAndFlush(any())).thenReturn(new InventoryEntity());
        when(this.inventoryMapper.toDto(any())).thenReturn(new CreateInventoryDto());

        ResponseWrapperDto<CreateInventoryDto> result = this.itemInventoryServiceImpl.createInventory(mockInventoryDto());

        assertEquals(ActiveStatus.ACTIVE.getValue(),result.getStatus());
        verify(this.itemRepository).findById(anyLong());
        verify(this.inventoryMapper).toEntity(any());
        verify(this.inventoryMapper).toDto(any());
        verify(this.itemInventoryRepository).saveAndFlush(any());
    }

    @Test
    void createInventoryIfInventoryTypeIsGoodsOut(){
        GoodsEntity existingGoodsEntity = new GoodsEntity();
        existingGoodsEntity.setTotalQuantity(100F);
        existingGoodsEntity.setUnitsEntity(mockUnitEntity());

        when(this.itemRepository.findById(anyLong())).thenReturn(Optional.of(existingGoodsEntity));
        when(this.inventoryMapper.toEntity(any())).thenReturn(new InventoryEntity());
        when(this.itemInventoryRepository.saveAndFlush(any())).thenReturn(new InventoryEntity());
        when(this.inventoryMapper.toDto(any())).thenReturn(new CreateInventoryDto());

        CreateInventoryDto createInventoryDto = mockInventoryDto();
        createInventoryDto.setInventoryType(InventoryType.GOODS_OUT);
        ResponseWrapperDto<CreateInventoryDto> result = this.itemInventoryServiceImpl.createInventory(createInventoryDto);

        assertEquals(ActiveStatus.ACTIVE.getValue(),result.getStatus());
        verify(this.itemRepository).findById(anyLong());
        verify(this.inventoryMapper).toEntity(any());
        verify(this.inventoryMapper).toDto(any());
        verify(this.itemInventoryRepository).saveAndFlush(any());
    }

    @Test
    void createInventoryIfInventoryTypeIsGoodsOutAndTotalQuantityExceed(){
        GoodsEntity existingGoodsEntity = new GoodsEntity();
        existingGoodsEntity.setTotalQuantity(100F);
        existingGoodsEntity.setUnitsEntity(mockUnitEntity());

        when(this.itemRepository.findById(anyLong())).thenReturn(Optional.of(existingGoodsEntity));
        when(this.inventoryMapper.toEntity(any())).thenReturn(new InventoryEntity());
        when(this.itemInventoryRepository.saveAndFlush(any())).thenReturn(new InventoryEntity());
        when(this.inventoryMapper.toDto(any())).thenReturn(new CreateInventoryDto());

        CreateInventoryDto createInventoryDto = mockInventoryDto();
        createInventoryDto.setInventoryType(InventoryType.GOODS_OUT);
        createInventoryDto.setQuantity(200F);

        ItemQuantityException result = assertThrows(ItemQuantityException.class, () -> {
            this.itemInventoryServiceImpl.createInventory(createInventoryDto);
        });

        String message = "Item Quantity exceeded.";
        assertEquals(message,result.getMessage());

        verify(this.itemRepository).findById(anyLong());
        verify(this.inventoryMapper).toEntity(any());
        verify(this.inventoryMapper,never()).toDto(any());
        verify(this.itemInventoryRepository,never()).saveAndFlush(any());
    }

    @Test
    void getAllInventoryWhenInventoryFilterAll(){
        List<InventoryResponseDto> mocKList = new ArrayList<>();
        when(this.itemInventoryRepository.findAllByInventory(anyLong(),anyBoolean(),any(),any())).thenReturn(new PageImpl<>(mocKList));

        ResponseWrapperDto<InventoryPaginationDto<List<InventoryResponseDto>>> result =
                this.itemInventoryServiceImpl.getAllInventory(1L, InventoryFilter.ALL, 0, 10);
        assertEquals(ActiveStatus.ACTIVE.getValue(), result.getStatus());
        verify(this.itemInventoryRepository).findAllByInventory(anyLong(),anyBoolean(),any(),any());
    }

    @Test
    void getAllInventoryWhenInventoryFilterIn(){
        List<InventoryResponseDto> mocKList = new ArrayList<>();
        when(this.itemInventoryRepository.findAllByInventory(anyLong(),anyBoolean(),any(),any())).thenReturn(new PageImpl<>(mocKList));

        ResponseWrapperDto<InventoryPaginationDto<List<InventoryResponseDto>>> result =
                this.itemInventoryServiceImpl.getAllInventory(1L, InventoryFilter.IN, 0, 10);
        assertEquals(ActiveStatus.ACTIVE.getValue(), result.getStatus());
        verify(this.itemInventoryRepository).findAllByInventory(anyLong(),anyBoolean(),any(),any());
    }

    @Test
    void getAllInventoryWhenInventoryFilterOut(){
        List<InventoryResponseDto> mocKList = new ArrayList<>();
        when(this.itemInventoryRepository.findAllByInventory(anyLong(),anyBoolean(),any(),any())).thenReturn(new PageImpl<>(mocKList));

        ResponseWrapperDto<InventoryPaginationDto<List<InventoryResponseDto>>> result =
                this.itemInventoryServiceImpl.getAllInventory(1L, InventoryFilter.OUT, 0, 10);
        assertEquals(ActiveStatus.ACTIVE.getValue(), result.getStatus());
        verify(this.itemInventoryRepository).findAllByInventory(anyLong(),anyBoolean(),any(),any());
    }

    private UnitsEntity mockUnitEntity() {
        UnitsEntity unitsEntity= new UnitsEntity();
        unitsEntity.setId(1L);
        unitsEntity.setName("unit");
        return unitsEntity;
    }

    private CreateInventoryDto mockInventoryDto(){
        CreateInventoryDto createInventoryDto = new CreateInventoryDto();
        createInventoryDto.setGoodsId(123L);
        createInventoryDto.setInventoryType(InventoryType.GOODS_IN);
        createInventoryDto.setQuantity(100F);
        return createInventoryDto;
    }


}

