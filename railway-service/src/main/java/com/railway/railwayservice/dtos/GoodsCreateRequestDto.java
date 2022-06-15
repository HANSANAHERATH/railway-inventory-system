package com.railway.railwayservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCreateRequestDto implements Serializable {
    private long id;
    private String goodName;
    private String description;
    private long unitId;
    private String date;
    private String userId;
    private int category;
    private float minQuantity;
}
