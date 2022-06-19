package com.railway.railwayservice.Exceptions;

/**
 * The type Item quantity exception.
 */
public class ItemQuantityException extends RuntimeException{
    private static final String ERROR = "Item Quantity exceeded.";

    /**
     * Instantiates a new Item quantity exception.
     */
    public ItemQuantityException(){
        super(ERROR);
    }
}
