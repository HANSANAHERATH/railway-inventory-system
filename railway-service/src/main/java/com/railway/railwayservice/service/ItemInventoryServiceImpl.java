package com.railway.railwayservice.service;

import com.railway.railwayservice.Exceptions.ItemCountDecrementException;
import com.railway.railwayservice.Exceptions.ItemNotFoundException;
import com.railway.railwayservice.Exceptions.ItemQuantityException;
import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.dtos.CreateInventoryResponseDto;
import com.railway.railwayservice.dtos.GetAllInventoryResponseDto;
import com.railway.railwayservice.dtos.LookupResponseDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.ItemUnits;
import com.railway.railwayservice.entity.ItemsEntity;
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
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemInventoryServiceImpl implements ItemInventory{

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemInventoryRepository itemInventoryRepository;

    @Override
    public ResponseWrapperDto getItemAll() {
        return null;
    }

    @Override
    public ResponseWrapperDto getItemLookup(String itemName) throws Exception{
        List<ItemsEntity> itemsEntities = itemRepository.findByItemNameIsContainingAndIsDeleted(itemName,false);
        List<LookupResponseDto> lookupResponseDtos = new ArrayList<>();

        itemsEntities.stream().forEach(i -> {
            LookupResponseDto lookupResponseDto = new LookupResponseDto();
            lookupResponseDto.setId(i.getId());
            lookupResponseDto.setItemName(i.getItemName());
            lookupResponseDto.setItemUnits(i.getItemUnits());
            lookupResponseDto.setBalance(i.getBalance());
            lookupResponseDtos.add(lookupResponseDto);
        });
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(true,"Fetch Success.", lookupResponseDtos);
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto createInventory(CreateInventoryDto createInventoryDto) throws ItemNotFoundException, ItemCountDecrementException, ItemQuantityException {

        Optional<ItemsEntity> existingItemEntity = itemRepository.findById(createInventoryDto.getItemsEntityId());
        if(!existingItemEntity.isPresent()){
            throw new ItemNotFoundException();
        }

        if(existingItemEntity.get().getBalance() < createInventoryDto.getQuantity()){
            throw new ItemQuantityException();
        }

        com.railway.railwayservice.entity.ItemInventory itemInventory = new com.railway.railwayservice.entity.ItemInventory();

        itemInventory.setId(null);
        itemInventory.setDate(LocalDate.now());
        itemInventory.setTime(LocalTime.now());
        itemInventory.setAdditionalNote(createInventoryDto.getAdditionalNote());
        itemInventory.setDescription(createInventoryDto.getDescription());
        itemInventory.setHandoverTo(createInventoryDto.getHandoverTo());
        itemInventory.setQuantity(createInventoryDto.getQuantity());
        itemInventory.setReference(createInventoryDto.getReference());
        itemInventory.setShedStoreNo(createInventoryDto.getShedStoreNo());
        itemInventory.setSupervisorName(createInventoryDto.getSupervisorName());

        ItemUnits itemUnits = new ItemUnits();
        itemUnits.setId(createInventoryDto.getUnitId());
       // itemInventory.setItemUnits(itemUnits);

        ItemsEntity itemsEntity = new ItemsEntity();
        itemsEntity.setId(createInventoryDto.getItemsEntityId());
       // itemsEntity.addItemInventory(itemInventory);
        existingItemEntity.get().setBalance(existingItemEntity.get().getBalance() - createInventoryDto.getQuantity());
        itemInventory.setItemsEntity(existingItemEntity.get());

        com.railway.railwayservice.entity.ItemInventory savedEntity = itemInventoryRepository.saveAndFlush(itemInventory);

        GetAllInventoryResponseDto getAllInventoryResponseDto = new GetAllInventoryResponseDto();
        getAllInventoryResponseDto.setDescription(savedEntity.getDescription());
        getAllInventoryResponseDto.setDate(savedEntity.getDate().toString());
        getAllInventoryResponseDto.setAdditionalNote(savedEntity.getAdditionalNote());
        getAllInventoryResponseDto.setQuantity(savedEntity.getQuantity());
        getAllInventoryResponseDto.setReference(savedEntity.getReference());
        getAllInventoryResponseDto.setHandoverTo(savedEntity.getHandoverTo());
        getAllInventoryResponseDto.setId(savedEntity.getId());
        getAllInventoryResponseDto.setTime(savedEntity.getTime().toString());
        getAllInventoryResponseDto.setSupervisorName(savedEntity.getSupervisorName());
        getAllInventoryResponseDto.setShedStoreNo(savedEntity.getShedStoreNo());

        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(true,"Create Success.", getAllInventoryResponseDto);
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto getAllInventory(Long id) throws Exception {
        ItemsEntity itemsEntity = new ItemsEntity();
        itemsEntity.setId(id);
        List<com.railway.railwayservice.entity.ItemInventory>  list = itemInventoryRepository.findAllByItemsEntity(itemsEntity);

        List<GetAllInventoryResponseDto> resDtoList = new ArrayList<>();

        for(com.railway.railwayservice.entity.ItemInventory itemInventory : list){
            GetAllInventoryResponseDto getAllInventoryResponseDto = new GetAllInventoryResponseDto();
            getAllInventoryResponseDto.setDescription(itemInventory.getDescription());
            getAllInventoryResponseDto.setDate(itemInventory.getDate().toString());
            getAllInventoryResponseDto.setAdditionalNote(itemInventory.getAdditionalNote());
            getAllInventoryResponseDto.setQuantity(itemInventory.getQuantity());
            getAllInventoryResponseDto.setReference(itemInventory.getReference());
            getAllInventoryResponseDto.setHandoverTo(itemInventory.getHandoverTo());
            getAllInventoryResponseDto.setId(itemInventory.getId());
            getAllInventoryResponseDto.setTime(itemInventory.getTime().toString());
            getAllInventoryResponseDto.setSupervisorName(itemInventory.getSupervisorName());
            getAllInventoryResponseDto.setShedStoreNo(itemInventory.getShedStoreNo());
            //getAllInventoryResponseDto.setUnitId(itemInventory.getItemUnits().getId());
            resDtoList.add(getAllInventoryResponseDto);
        }


        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(true,"Fetch Success.", resDtoList);
        return responseWrapperDto;
    }
}
