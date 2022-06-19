package com.railway.railwayservice.entity;

import com.railway.railwayservice.enums.InventoryType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The type Inventory entity.
 */
@Entity
@Data
@Table(name = "inventory")
public class InventoryEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime time;
    private String shedStoreNo;
    private String description;
    private Float quantity;
    private String supervisorName;
    private String handoverTo;

    @Column(name = "inventory_type", length = 10)
    @Enumerated(EnumType.STRING)
    private InventoryType inventoryType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "goods_id")
    private GoodsEntity goodsEntity;

    @OneToOne(targetEntity = UnitsEntity.class)
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private UnitsEntity unitsEntity;
}
