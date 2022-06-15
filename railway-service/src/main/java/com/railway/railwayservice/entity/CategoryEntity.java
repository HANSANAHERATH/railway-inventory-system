package com.railway.railwayservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "category")
public class CategoryEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
}
