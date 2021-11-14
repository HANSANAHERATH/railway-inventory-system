package com.railway.railwayservice.Exceptions;

public class ItemQuantityException extends RuntimeException{
    public ItemQuantityException(){
        super("Item Quantity exceeded.");
    }
}
