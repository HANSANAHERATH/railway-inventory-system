package com.railway.railwayservice.dtos;

import lombok.Data;

@Data
public class GetAllInventoryResponseDto {
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
    private Long unitId;
}
