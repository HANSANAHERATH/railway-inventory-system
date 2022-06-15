package com.railway.railwayservice.Exceptions;

import com.railway.railwayservice.dtos.common.ResponseWrapperDto;

public class InputNotValidException extends RuntimeException{
    public InputNotValidException(ResponseWrapperDto responseWrapperDto){
        super(responseWrapperDto.getStatusMessage());
    }
}
