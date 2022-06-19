package com.railway.railwayservice.repository;

import com.railway.railwayservice.entity.UnitsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Unit repository.
 */
public interface UnitRepository extends JpaRepository<UnitsEntity, Long> {
}
