package com.railway.railwayservice.service;

import com.railway.railwayservice.Exceptions.ItemNotFoundException;
import com.railway.railwayservice.Exceptions.ItemQuantityException;
import com.railway.railwayservice.dtos.*;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.InventoryEntity;
import com.railway.railwayservice.enums.InventoryFilter;
import com.railway.railwayservice.enums.InventoryType;
import com.railway.railwayservice.repository.ItemInventoryRepository;
import com.railway.railwayservice.repository.ItemRepository;
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

    private final ItemRepository itemRepository;

    private final ItemInventoryRepository itemInventoryRepository;

    @Override
    public ResponseWrapperDto getItemAll() {
        return null;
    }

    @Override
    public ResponseWrapperDto getItemLookup(long category) {
        CategoryEntity itemCategory = new CategoryEntity();
        itemCategory.setId(category);
        List<GoodNameLookupResponseDto> goodNameLookupResponseDtos = itemRepository.findGoodsList(itemCategory, false);
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(true, "Fetch Success.", goodNameLookupResponseDtos);
        return responseWrapperDto;
    }

    @Override
    @Transactional
    public ResponseWrapperDto createInventory(CreateInventoryDto createInventoryDto) {
        Optional<GoodsEntity> existingGoodsEntity = itemRepository.findById(createInventoryDto.getGoodsId());
        if (!existingGoodsEntity.isPresent()) {
            throw new ItemNotFoundException();
        }

        InventoryEntity inventoryEntity = new InventoryEntity();

        if (createInventoryDto.getInventoryType().equals(InventoryType.GOODS_IN)) {
            inventoryEntity.setInventoryType(InventoryType.GOODS_IN);
            float total = existingGoodsEntity.get().getTotalQuantity() + createInventoryDto.getQuantity();
            existingGoodsEntity.get().setTotalQuantity(total);
        } else {
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
    public ResponseWrapperDto getAllInventory(Long id, InventoryFilter inventoryFilter, int page, int size) {
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
        Page<InventoryResponseDto> result = itemInventoryRepository.findAllByInventory(id, false, inventoryTypes, paging);
        InventoryPaginationDto inventoryPaginationDto = new InventoryPaginationDto(
                result.getTotalElements(),
                result.getTotalPages(),
                size,
                page,
                new ArrayList<InventoryEntity>());
        if (result.hasContent()) {
            inventoryPaginationDto.setContent(result.getContent());
        }
        // List<InventoryResponseDto> list = itemInventoryRepository.findAllByInventory(id, false, inventoryTypes);
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(true, "Fetch Success.", inventoryPaginationDto);
        return responseWrapperDto;
    }

    private BalanceDto getBalance(Long id) {
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
