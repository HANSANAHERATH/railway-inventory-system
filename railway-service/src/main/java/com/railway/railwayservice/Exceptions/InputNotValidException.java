package com.railway.railwayservice.Exceptions;

import com.railway.railwayservice.dtos.common.ResponseWrapperDto;

/**
 * The type Input not valid exception.
 */
public class InputNotValidException extends RuntimeException {
    /**
     * Instantiates a new Input not valid exception.
     *
     * @param responseWrapperDto the response wrapper dto
     */
    public InputNotValidException(ResponseWrapperDto responseWrapperDto) {
        super(responseWrapperDto.getStatusMessage());
    }
}
