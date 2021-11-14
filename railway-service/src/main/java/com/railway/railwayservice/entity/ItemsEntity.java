package com.railway.railwayservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "items")
public class ItemsEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String notes;
    private LocalDate date;

    @OneToOne(targetEntity = ItemUnits.class)
    @JoinColumn(name = "m_units", referencedColumnName = "id")
    private ItemUnits itemUnits;

    private boolean isDeleted = false;
    private float quantity;
    private float balance = 0;

    @OneToMany(mappedBy = "itemsEntity", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH })
    private Set<ItemInventory> itemInventory;

    public void addItemInventory(ItemInventory itemInventoryObj) {
        itemInventory.add(itemInventoryObj);
        itemInventoryObj.setItemsEntity(this);
    }

    public void removeItemInventory(ItemInventory itemInventoryObj) {
        itemInventory.remove(itemInventoryObj);
        itemInventoryObj.setItemsEntity(null);
    }
}
