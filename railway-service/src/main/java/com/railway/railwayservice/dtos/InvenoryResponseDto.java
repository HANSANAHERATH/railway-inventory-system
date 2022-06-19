package com.railway.railwayservice.dtos;

import lombok.Data;

import java.util.List;

/**
 * The type Invenory response dto.
 */
@Data
public class InvenoryResponseDto {
    private BalanceDto balanceDto;
    private List<GetAllInventoryResponseDto> getAllInventoryResponseDtos;
}
