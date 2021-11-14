package com.railway.railwayservice.Exceptions;

public class ItemCountDecrementException extends RuntimeException{
    public ItemCountDecrementException(){
        super("Can not decrement item quantity.");
    }
}
