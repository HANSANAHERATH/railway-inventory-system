package com.railway.railwayservice.Exceptions;

public class ItemNotFoundException extends  RuntimeException{
    public ItemNotFoundException(){
        super("Item Not Found");
    }

}
