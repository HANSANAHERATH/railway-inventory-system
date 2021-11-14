package com.railway.railwayservice.dtos;

import com.railway.railwayservice.entity.ItemInventory;
import com.railway.railwayservice.entity.ItemUnits;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class LookupResponseDto {
    private Long id;
    private String itemName;
    private String notes;
    private LocalDate date;
    private ItemUnits itemUnits;
    private boolean isDeleted;
    private float quantity;
    private float balance;
    private List<ItemInventory> itemInventory;

}
