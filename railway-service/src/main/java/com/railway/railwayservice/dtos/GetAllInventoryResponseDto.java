package com.railway.railwayservice.dtos;

import lombok.Data;

/**
 * The type Get all inventory response dto.
 */
@Data
public class GetAllInventoryResponseDto {
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
    private Long unitId;
}
