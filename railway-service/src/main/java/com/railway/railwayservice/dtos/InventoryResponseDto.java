package com.railway.railwayservice.dtos;

import com.railway.railwayservice.enums.InventoryType;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The interface Inventory response dto.
 */
public interface InventoryResponseDto {
    /**
     * Gets id.
     *
     * @return the id
     */
    Long getId();

    /**
     * Gets date.
     *
     * @return the date
     */
    LocalDate getDate();

    /**
     * Gets description.
     *
     * @return the description
     */
    String getDescription();

    /**
     * Gets handover to.
     *
     * @return the handover to
     */
    String getHandoverTo();

    /**
     * Gets shed store no.
     *
     * @return the shed store no
     */
    String getShedStoreNo();

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    Float getQuantity();

    /**
     * Gets supervisor name.
     *
     * @return the supervisor name
     */
    String getSupervisorName();

    /**
     * Gets time.
     *
     * @return the time
     */
    LocalTime getTime();

    /**
     * Gets unit.
     *
     * @return the unit
     */
    Long getUnit();

    /**
     * Gets total quantity.
     *
     * @return the total quantity
     */
    Float getTotalQuantity();

    /**
     * Gets inventory type.
     *
     * @return the inventory type
     */
    InventoryType getInventoryType();
}
