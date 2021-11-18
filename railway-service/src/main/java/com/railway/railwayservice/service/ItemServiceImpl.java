package com.railway.railwayservice.service;

import com.railway.railwayservice.Exceptions.ItemAllReadyExistingException;
import com.railway.railwayservice.Exceptions.ItemCountDecrementException;
import com.railway.railwayservice.Exceptions.ItemNotFoundException;
import com.railway.railwayservice.Exceptions.RuntimeExceptionHere;
import com.railway.railwayservice.dtos.ItemCreateRequestDto;
import com.railway.railwayservice.dtos.LookupResponseDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.ItemUnits;
import com.railway.railwayservice.entity.ItemsEntity;
import com.railway.railwayservice.repository.ItemRepository;
import com.railway.railwayservice.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Transactional
    @Override
    public ResponseWrapperDto createItem(ItemCreateRequestDto itemCreateRequestDto) throws RuntimeExceptionHere {
        try {
            ResponseWrapperDto responseWrapperDto = null;

            Optional<ItemsEntity> isExisting = itemRepository.findByItemNameAndIsDeleted(itemCreateRequestDto.getItemName(), false);
            if(isExisting.isPresent()){
                throw new ItemAllReadyExistingException();
            }

            ItemUnits itemUnits = new ItemUnits();
            itemUnits.setId(itemCreateRequestDto.getUnitId());

            ItemsEntity itemsEntity = new ItemsEntity();
            itemsEntity.setItemName(itemCreateRequestDto.getItemName());
            itemsEntity.setNotes(itemCreateRequestDto.getNotes());
            itemsEntity.setQuantity(itemCreateRequestDto.getQuantity());
            itemsEntity.setItemUnits(itemUnits);
            itemsEntity.setDate(stringDateToLocalDateTime(itemCreateRequestDto.getDate()));
            itemsEntity.setBalance(itemCreateRequestDto.getQuantity());
            ItemsEntity response = itemRepository.saveAndFlush(itemsEntity);

            responseWrapperDto = new ResponseWrapperDto(true, "Saved Successful", null);
            return responseWrapperDto;
        }catch (Exception e){
            throw new RuntimeExceptionHere(e);
        }
    }

    @Transactional
    @Override
    public ResponseWrapperDto updateItem(ItemCreateRequestDto itemCreateRequestDto, long id) {
        ResponseWrapperDto responseWrapperDto = null;

        Optional<ItemsEntity> isExisting = itemRepository.findByIdAndIsDeleted(id, false);
        if(!isExisting.isPresent()){
            throw new ItemNotFoundException();
        }

        ItemUnits itemUnits = new ItemUnits();
        itemUnits.setId(itemCreateRequestDto.getUnitId());

        ItemsEntity itemsEntity = new ItemsEntity();
        itemsEntity.setId(id);
        itemsEntity.setItemName(itemCreateRequestDto.getItemName());
        itemsEntity.setNotes(itemCreateRequestDto.getNotes());
        itemsEntity.setQuantity(itemCreateRequestDto.getQuantity());
        itemsEntity.setItemUnits(itemUnits);

        float balance = isExisting.get().getBalance();
        float diff = itemCreateRequestDto.getQuantity() - isExisting.get().getQuantity();
        balance = balance + diff;

        if(diff < 0){
            throw new ItemCountDecrementException();
        }

        itemsEntity.setBalance(balance);

        itemsEntity.setDate(stringDateToLocalDateTime(itemCreateRequestDto.getDate()));

        ItemsEntity response = itemRepository.saveAndFlush(itemsEntity);

        responseWrapperDto = new ResponseWrapperDto(true, "Update Successful", response);
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto deleteItem(long id) {
        ResponseWrapperDto responseWrapperDto = null;

        Optional<ItemsEntity> isExisting = itemRepository.findByIdAndIsDeleted(id, false);
        if(!isExisting.isPresent()){
            throw new ItemNotFoundException();
        }

        isExisting.get().setDeleted(true);

        ItemsEntity response = itemRepository.saveAndFlush(isExisting.get());

        if(response.getId() == null){

        }

        responseWrapperDto = new ResponseWrapperDto(true, "Delete Successful", response);
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto getItem(long id) {
        ResponseWrapperDto responseWrapperDto = null;

        Optional<ItemsEntity> isExisting = itemRepository.findByIdAndIsDeleted(id, false);
        if(!isExisting.isPresent()){
            throw new ItemNotFoundException();
        }

        responseWrapperDto = new ResponseWrapperDto(true,"Get Item Successful" ,isExisting);
        return  responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto getAllItem() {
        ResponseWrapperDto responseWrapperDto = null;

        List<ItemsEntity> isExisting = itemRepository.findByIsDeleted(false);
        List<LookupResponseDto> responseList = new ArrayList<>();
        isExisting.forEach(it -> {
            LookupResponseDto itemResponseDto = new LookupResponseDto();
            itemResponseDto.setId(it.getId());
            itemResponseDto.setItemName(it.getItemName());
            itemResponseDto.setItemUnits(it.getItemUnits());
            itemResponseDto.setBalance(it.getBalance());
            itemResponseDto.setDate(it.getDate());
            itemResponseDto.setDeleted(it.isDeleted());
            itemResponseDto.setNotes(it.getNotes());
            itemResponseDto.setQuantity(it.getQuantity());
            responseList.add(itemResponseDto);
        });
       responseWrapperDto = new ResponseWrapperDto(true, "Get All Items Successful", responseList);
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto getUnitList() {
        List<ItemUnits> unitList = unitRepository.findAll();
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(true,"success",unitList);
        return responseWrapperDto;
    }

    private LocalDate stringDateToLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}
