package com.railway.railwayservice.Exceptions;

/**
 * The type Item count decrement exception.
 */
public class ItemCountDecrementException extends RuntimeException {
    private static final String ERROR = "Can not decrement item quantity.";

    /**
     * Instantiates a new Item count decrement exception.
     */
    public ItemCountDecrementException() {
        super(ERROR);
    }
}
