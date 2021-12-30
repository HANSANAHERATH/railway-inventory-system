package com.railway.railwayservice.dtos;

import com.railway.railwayservice.enums.InventoryType;
import lombok.Data;

import javax.persistence.Enumerated;

@Data
public class CreateInventoryDto {
    private Long id;
    private String date;
    private String time;
    private String shedStoreNo;
    private String description;
    private float quantity;
    private String supervisorName;
    private String handoverTo;
    private Long goodsId;
    private InventoryType inventoryType;
}
