package com.railway.railwayservice.dtos;

import com.railway.railwayservice.entity.UnitsEntity;
import com.railway.railwayservice.enums.InventoryType;

import java.time.LocalDate;
import java.time.LocalTime;

public interface InventoryResponseDto {
    Long getId();
    LocalDate getDate();
    String getDescription();
    String getHandoverTo();
    String getShedStoreNo();
    Float getQuantity();
    String getSupervisorName();
    LocalTime getTime();
    Long getUnit();
    Float getTotalQuantity();
    InventoryType getInventoryType();
}
