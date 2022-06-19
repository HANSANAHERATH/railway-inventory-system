package com.railway.railwayservice.dtos;

import com.railway.railwayservice.entity.UnitsEntity;

/**
 * The interface Good name lookup response dto.
 */
public interface GoodNameLookupResponseDto {
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
     * Gets units entity.
     *
     * @return the units entity
     */
    UnitsEntity getUnitsEntity();
}
