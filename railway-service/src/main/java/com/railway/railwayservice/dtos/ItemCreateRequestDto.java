package com.railway.railwayservice.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemCreateRequestDto implements Serializable {
    private long id;
    private String itemName;
    private String notes;
    private float quantity;
    private long unitId;
    private String date;
    private String userId;
}
