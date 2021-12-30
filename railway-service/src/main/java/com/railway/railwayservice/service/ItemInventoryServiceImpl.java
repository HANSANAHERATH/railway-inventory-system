package com.railway.railwayservice.service;

import com.railway.railwayservice.Exceptions.ItemCountDecrementException;
import com.railway.railwayservice.Exceptions.ItemNotFoundException;
import com.railway.railwayservice.Exceptions.ItemQuantityException;
import com.railway.railwayservice.Exceptions.RuntimeExceptionHere;
import com.railway.railwayservice.dtos.*;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.InventoryEntity;
import com.railway.railwayservice.enums.InventoryFilter;
import com.railway.railwayservice.enums.InventoryType;
import com.railway.railwayservice.repository.ItemInventoryRepository;
import com.railway.railwayservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemInventoryServiceImpl implements ItemInventory {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemInventoryRepository itemInventoryRepository;

    @Override
    public ResponseWrapperDto getItemAll() {
        return null;
    }

    @Override
    public ResponseWrapperDto getItemLookup(long category) throws RuntimeExceptionHere {
        CategoryEntity itemCategory = new CategoryEntity();
        itemCategory.setId(category);
        List<GoodNameLookupResponseDto> goodNameLookupResponseDtos = itemRepository.findGoodsList(itemCategory, false);
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(true, "Fetch Success.", goodNameLookupResponseDtos);
        return responseWrapperDto;
    }

    @Override
    @Transactional
    public ResponseWrapperDto createInventory(CreateInventoryDto createInventoryDto) throws ItemNotFoundException, ItemCountDecrementException, ItemQuantityException {
        Optional<GoodsEntity> existingGoodsEntity = itemRepository.findById(createInventoryDto.getGoodsId());
        if (!existingGoodsEntity.isPresent()) {
            throw new ItemNotFoundException();
        }

        InventoryEntity inventoryEntity = new InventoryEntity();

        if(createInventoryDto.getInventoryType().equals(InventoryType.GOODS_IN)){
            inventoryEntity.setInventoryType(InventoryType.GOODS_IN);
            float total = existingGoodsEntity.get().getTotalQuantity() + createInventoryDto.getQuantity();
            existingGoodsEntity.get().setTotalQuantity(total);
        }else {
            inventoryEntity.setInventoryType(InventoryType.GOODS_OUT);
            if (existingGoodsEntity.get().getTotalQuantity() < createInventoryDto.getQuantity()) {
                throw new ItemQuantityException();
            }
            float total = existingGoodsEntity.get().getTotalQuantity() - createInventoryDto.getQuantity();
            existingGoodsEntity.get().setTotalQuantity(total);
        }

        inventoryEntity.setId(null);
        inventoryEntity.setDate(LocalDate.now());
        inventoryEntity.setTime(LocalTime.now());
        inventoryEntity.setDescription(createInventoryDto.getDescription());
        inventoryEntity.setHandoverTo(createInventoryDto.getHandoverTo());
        inventoryEntity.setQuantity(createInventoryDto.getQuantity());
        inventoryEntity.setShedStoreNo(createInventoryDto.getShedStoreNo());
        inventoryEntity.setSupervisorName(createInventoryDto.getSupervisorName());
        inventoryEntity.setUnitsEntity(existingGoodsEntity.get().getUnitsEntity());
        inventoryEntity.setGoodsEntity(existingGoodsEntity.get());
        InventoryEntity savedEntity = itemInventoryRepository.saveAndFlush(inventoryEntity);
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(true, "Create Success.", null);
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto getAllInventory(Long id, InventoryFilter inventoryFilter) throws RuntimeExceptionHere {
        List<String> inventoryTypes = new ArrayList<>();
        if(inventoryFilter.equals(InventoryFilter.ALL)){
            inventoryTypes.add(InventoryType.GOODS_IN.toString());
            inventoryTypes.add(InventoryType.GOODS_OUT.toString());
        }else {
            if (inventoryFilter.equals(InventoryFilter.IN))
                inventoryTypes.add(InventoryType.GOODS_IN.toString());
            else
                inventoryTypes.add(InventoryType.GOODS_OUT.toString());
        }

        List<InventoryResponseDto> list = itemInventoryRepository.findAllByInventory(id, false, inventoryTypes);
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(true, "Fetch Success.", list);
        return responseWrapperDto;
    }
    
    private BalanceDto getBalance(Long id) throws ItemQuantityException{
       /* Optional<ItemsEntity> entity = itemRepository.findByIdAndIsDeleted(id, false);
        if(!entity.isPresent()){
            throw new ItemNotFoundException();
        }
        BalanceDto balanceDto = new BalanceDto();
        balanceDto.setBalance(Float.toString(entity.get().getBalance()));
        balanceDto.setTotalQuantity(Float.toString(entity.get().getQuantity()));*/
        return null;
    }
}
