package com.railway.railwayservice.Exceptions;

/**
 * The type Item not found exception.
 */
public class ItemNotFoundException extends RuntimeException {
    private static final String ITEM_NOT_FOUND = "Item Not Found";

    /**
     * Instantiates a new Item not found exception.
     */
    public ItemNotFoundException() {
        super(ITEM_NOT_FOUND);
    }

}
