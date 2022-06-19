package com.railway.railwayservice.dtos;

import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.UnitsEntity;

import java.time.LocalDate;

/**
 * The interface Goods entity response dto.
 */
public interface GoodsEntityResponseDto {
    /**
     * Gets id.
     *
     * @return the id
     */
    Long getId();

    /**
     * Gets name.
     *
     * @return the name
     */
    String getName();

    /**
     * Gets description.
     *
     * @return the description
     */
    String getDescription();

    /**
     * Gets date.
     *
     * @return the date
     */
    LocalDate getDate();

    /**
     * Gets min quantity.
     *
     * @return the min quantity
     */
    Float getMinQuantity();

    /**
     * Gets total quantity.
     *
     * @return the total quantity
     */
    Float getTotalQuantity();

    /**
     * Gets units.
     *
     * @return the units
     */
    UnitsEntity getUnits();

    /**
     * Gets category.
     *
     * @return the category
     */
    CategoryEntity getCategory();
}
