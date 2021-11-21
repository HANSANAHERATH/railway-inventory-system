package com.railway.railwayservice.dtos;

import lombok.Data;

import java.util.List;

@Data
public class InvenoryResponseDto {
    private BalanceDto balanceDto;
    private List<GetAllInventoryResponseDto> getAllInventoryResponseDtos;
}
