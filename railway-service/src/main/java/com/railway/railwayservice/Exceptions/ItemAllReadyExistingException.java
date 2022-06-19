package com.railway.railwayservice.Exceptions;

/**
 * The type Item all ready existing exception.
 */
public class ItemAllReadyExistingException extends RuntimeException {
    private static final String ITEM_ALREADY_EXIST = "Item Already Available";

    /**
     * Instantiates a new Item all ready existing exception.
     */
    public ItemAllReadyExistingException() {
        super(ITEM_ALREADY_EXIST);
    }
}
