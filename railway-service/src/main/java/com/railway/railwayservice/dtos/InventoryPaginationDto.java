package com.railway.railwayservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Inventory pagination dto.
 *
 * @param <T> the type parameter
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryPaginationDto<T> {
    private Long totalCount;
    private Integer totalPage;
    private Integer pageSize;
    private Integer page;
    private T content;
}
