package com.railway.railwayservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Goods create request dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCreateRequestDto implements Serializable {
    private Long id;
    private String goodName;
    private String description;
    private Long unitId;
    private String date;
    private String userId;
    private Integer category;
    private Float minQuantity;
}
