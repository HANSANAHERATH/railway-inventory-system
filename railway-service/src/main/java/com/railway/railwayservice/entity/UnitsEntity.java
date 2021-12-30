package com.railway.railwayservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "units")
public class UnitsEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;


}
