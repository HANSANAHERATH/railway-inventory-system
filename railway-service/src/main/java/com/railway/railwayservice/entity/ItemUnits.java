package com.railway.railwayservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "item_units")
public class ItemUnits implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unitName;

   // @OneToOne(mappedBy = "itemUnits")
   // private ItemsEntity itemsEntity;

}
