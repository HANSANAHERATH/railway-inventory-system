package com.railway.railwayservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Data
@Table(name = "item_inventory")
public class ItemInventory implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime time;
    private String reference;
    private String shedStoreNo;
    private String description;
    private float quantity;
    private String supervisorName;
    private String handoverTo;
    private String additionalNote;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "items_entity_id")
    private ItemsEntity itemsEntity;

    @OneToOne(targetEntity = ItemUnits.class)
    @JoinColumn(name = "m_units", referencedColumnName = "id")
    private ItemUnits itemUnits;
}
