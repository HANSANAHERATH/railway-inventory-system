package com.railway.railwayservice.repository;

import com.railway.railwayservice.entity.ItemUnits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<ItemUnits,Long> {
}
