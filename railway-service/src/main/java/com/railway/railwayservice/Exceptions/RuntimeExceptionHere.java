package com.railway.railwayservice.Exceptions;

public class RuntimeExceptionHere extends RuntimeException {
    public RuntimeExceptionHere(Exception ex){
        super(ex.getMessage());
    }
}
