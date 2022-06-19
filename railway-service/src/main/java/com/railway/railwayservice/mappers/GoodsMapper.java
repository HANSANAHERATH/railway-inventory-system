package com.railway.railwayservice.mappers;

import com.railway.railwayservice.dtos.GoodsCreateRequestDto;
import com.railway.railwayservice.entity.GoodsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The interface Goods mapper.
 */
@Mapper(componentModel = "spring")
public interface GoodsMapper {

    /**
     * To entity goods entity.
     *
     * @param goodsCreateRequestDto the goods create request dto
     * @return the goods entity
     */
    @Mapping(source = "goodName", target = "name")
    GoodsEntity toEntity(GoodsCreateRequestDto goodsCreateRequestDto);

    /**
     * To dto goods create request dto.
     *
     * @param goodsEntity the goods entity
     * @return the goods create request dto
     */
    @Mapping(source = "name", target = "goodName")
    @Mapping(source = "goodsEntity.unitsEntity.id", target = "unitId")
    @Mapping(source = "goodsEntity.categoryEntity.id", target = "category")
    GoodsCreateRequestDto toDto(GoodsEntity goodsEntity);
}
