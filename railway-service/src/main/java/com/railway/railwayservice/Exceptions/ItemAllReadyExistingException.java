package com.railway.railwayservice.Exceptions;

public class ItemAllReadyExistingException extends RuntimeException{
    public ItemAllReadyExistingException(){
        super("Item Already Available");
    }
}
