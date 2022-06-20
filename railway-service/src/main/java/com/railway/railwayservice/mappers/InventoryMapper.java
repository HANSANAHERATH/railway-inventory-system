package com.railway.railwayservice.mappers;

import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.entity.InventoryEntity;
import org.mapstruct.Mapper;

/**
 * The interface Inventory mapper.
 */
@Mapper(componentModel = "spring")
public interface InventoryMapper {

    /**
     * To entity inventory entity.
     *
     * @param createInventoryDto the create inventory dto
     * @return the inventory entity
     */
    InventoryEntity toEntity(CreateInventoryDto createInventoryDto);

    /**
     * To dto create inventory dto.
     *
     * @param inventoryEntity the inventory entity
     * @return the create inventory dto
     */
    CreateInventoryDto toDto(InventoryEntity inventoryEntity);
}
