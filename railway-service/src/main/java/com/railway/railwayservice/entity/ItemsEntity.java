package com.railway.railwayservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
