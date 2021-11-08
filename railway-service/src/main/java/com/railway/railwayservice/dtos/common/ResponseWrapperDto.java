package com.railway.railwayservice.dtos.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseWrapperDto<T> {
    private boolean status;
    private String statusMessage;
    private T body;

    public ResponseWrapperDto(boolean status, String statusMessage, T body){
        this.status = status;
        this.statusMessage = statusMessage;
        this.body = body;
    }
}
