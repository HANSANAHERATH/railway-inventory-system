package com.railway.railwayservice.repository;

import com.railway.railwayservice.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepository extends JpaRepository<CategoryEntity,Long> {
}
