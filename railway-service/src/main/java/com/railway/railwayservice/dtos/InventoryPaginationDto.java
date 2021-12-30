package com.railway.railwayservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryPaginationDto<T> {
    private long totalCount;
    private int totalPage;
    private int pageSize;
    private int page;
    private T content;
}
