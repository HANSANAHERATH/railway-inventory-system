package com.railway.railwayservice.repository;

import com.railway.railwayservice.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Item category repository.
 */
public interface ItemCategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
