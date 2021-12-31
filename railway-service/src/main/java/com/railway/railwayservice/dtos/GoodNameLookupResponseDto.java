package com.railway.railwayservice.dtos;

import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.InventoryEntity;
import com.railway.railwayservice.entity.UnitsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

public interface GoodNameLookupResponseDto {
    Long getId();
    String getName();
    UnitsEntity getUnitsEntity();
}
