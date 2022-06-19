package com.railway.railwayservice.dtos;

import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.UnitsEntity;
import lombok.Data;

/**
 * The type Create inventory response dto.
 */
@Data
public class CreateInventoryResponseDto {
    private Long id;
    private String date;
    private String time;
    private String reference;
    private String shedStoreNo;
    private String description;
    private Float quantity;
    private String supervisorName;
    private String handoverTo;
    private String additionalNote;
    private GoodsEntity goodsEntity;
    private UnitsEntity unitsEntity;
}
