package com.railway.railwayservice.dtos;

import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.UnitsEntity;

import java.time.LocalDate;
import java.util.HashMap;

public interface GoodsEntityResponseDto {
    Long getId();
    String getName();
    String getDescription();
    LocalDate getDate();
    Float getMinQuantity();
    Float getTotalQuantity();
    UnitsEntity getUnits();
    CategoryEntity getCategory();
}
