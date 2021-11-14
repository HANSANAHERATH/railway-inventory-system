package com.railway.railwayservice.repository;

import com.railway.railwayservice.entity.ItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemsEntity,Long> {
    Optional<ItemsEntity> findByItemNameAndIsDeleted(String name,boolean isDeleted);

    Optional<ItemsEntity> findByIdAndIsDeleted(long id, boolean isDeleted);

    List<ItemsEntity> findByIsDeleted(boolean IsDeleted);

    List<ItemsEntity> findByItemNameIsContainingAndIsDeleted(String name, boolean isDeleted);
}
