package com.railway.railwayservice.dtos;

import lombok.Data;

@Data
public class CreateInventoryDto {
    private Long id;
    private String date;
    private String time;
    private String reference;
    private String shedStoreNo;
    private String description;
    private float quantity;
    private String supervisorName;
    private String handoverTo;
    private String additionalNote;
    private Long itemsEntityId;
    private Long unitId;
}
