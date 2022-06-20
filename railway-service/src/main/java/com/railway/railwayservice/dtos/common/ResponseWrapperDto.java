package com.railway.railwayservice.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Response wrapper dto.
 *
 * @param <T> the type parameter
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseWrapperDto<T> {
    private Boolean status;
    private String statusMessage;
    private T body;
}
