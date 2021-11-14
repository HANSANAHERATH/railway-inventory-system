package com.railway.railwayservice.dtos;

import com.railway.railwayservice.entity.ItemUnits;
import com.railway.railwayservice.entity.ItemsEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateInventoryResponseDto {
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
    private ItemsEntity itemsEntity;
    private ItemUnits itemUnits;
}
