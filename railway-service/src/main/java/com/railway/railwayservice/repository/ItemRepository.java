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

/**
 * The interface Item repository.
 */
public interface ItemRepository extends JpaRepository<GoodsEntity, Long> {
    /**
     * Find by name and is deleted optional.
     *
     * @param name      the name
     * @param isDeleted the is deleted
     * @return the optional
     */
    Optional<GoodsEntity> findByNameAndIsDeleted(String name, boolean isDeleted);

    /**
     * Find by id and is deleted optional.
     *
     * @param id        the id
     * @param isDeleted the is deleted
     * @return the optional
     */
    Optional<GoodsEntity> findByIdAndIsDeleted(long id, boolean isDeleted);

    /**
     * Find goods entities page.
     *
     * @param itemCategory the item category
     * @param isDeleted    the is deleted
     * @param paging       the paging
     * @return the page
     */
    @Query(value = "SELECT g.id as id, g.name as name, g.description as description, g.date as date, g.minQuantity as minQuantity, g.totalQuantity as totalQuantity, g.unitsEntity as units, g.categoryEntity as category " +
            "FROM GoodsEntity g " +
            "WHERE g.categoryEntity = :itemCategory " +
            "AND g.isDeleted = :isDeleted " +
            "ORDER BY g.name ASC")
    Page<GoodsEntityResponseDto> findGoodsEntities(@Param("itemCategory") CategoryEntity itemCategory, @Param("isDeleted") boolean isDeleted, Pageable paging);

    /**
     * Find goods list list.
     *
     * @param itemCategory the item category
     * @param isDeleted    the is deleted
     * @return the list
     */
    @Query(value = "SELECT g.id as id, g.name as name, g.unitsEntity as unitsEntity " +
            "FROM GoodsEntity g " +
            "WHERE g.categoryEntity = :itemCategory " +
            "AND g.isDeleted = :isDeleted " +
            "ORDER BY g.name ASC")
    List<GoodNameLookupResponseDto> findGoodsList(@Param("itemCategory") CategoryEntity itemCategory, @Param("isDeleted") boolean isDeleted);
}
