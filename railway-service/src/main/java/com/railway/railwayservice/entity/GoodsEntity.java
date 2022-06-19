package com.railway.railwayservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * The type Goods entity.
 */
@Data
@Entity
@Table(name = "goods")
public class GoodsEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDate date;
    private Boolean isDeleted = false;
    private Float minQuantity = 0.0F;
    private Float totalQuantity = 0.0F;

    @OneToOne(targetEntity = UnitsEntity.class)
    @JoinColumn(name = "unit", referencedColumnName = "id")
    private UnitsEntity unitsEntity;

    @OneToOne(targetEntity = CategoryEntity.class)
    @JoinColumn(name = "category", referencedColumnName = "id")
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "goodsEntity", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,CascadeType.REFRESH })
    private Set<InventoryEntity> inventoryEntity;

    public void addInventory(InventoryEntity inventoryEntityObj) {
        inventoryEntity.add(inventoryEntityObj);
        inventoryEntityObj.setGoodsEntity(this);
    }

    public void removeInventory(InventoryEntity inventoryEntityObj) {
        inventoryEntity.remove(inventoryEntityObj);
        inventoryEntityObj.setGoodsEntity(null);
    }
}
