package com.railway.railwayservice.repository;

import com.railway.railwayservice.dtos.GoodNameLookupResponseDto;
import com.railway.railwayservice.dtos.GoodsEntityResponseDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<GoodsEntity,Long> {
    Optional<GoodsEntity> findByNameAndIsDeleted(String name,boolean isDeleted);

    Optional<GoodsEntity> findByIdAndIsDeleted(long id, boolean isDeleted);

    @Query(value = "SELECT g.id as id, g.name as name, g.description as description, g.date as date, g.minQuantity as minQuantity, g.totalQuantity as totalQuantity, g.unitsEntity as units, g.categoryEntity as category " +
            "FROM GoodsEntity g " +
            "WHERE g.categoryEntity = :itemCategory " +
            "AND g.isDeleted = :isDeleted " +
            "ORDER BY g.name ASC")
    Page<GoodsEntityResponseDto> findGoodsEntities(@Param("itemCategory") CategoryEntity itemCategory, @Param("isDeleted") boolean isDeleted, Pageable paging);

    @Query(value = "SELECT g.id as id, g.name as name, g.unitsEntity as unitsEntity " +
            "FROM GoodsEntity g " +
            "WHERE g.categoryEntity = :itemCategory " +
            "AND g.isDeleted = :isDeleted " +
            "ORDER BY g.name ASC")
    List<GoodNameLookupResponseDto> findGoodsList(@Param("itemCategory") CategoryEntity itemCategory, @Param("isDeleted") boolean isDeleted);
}
