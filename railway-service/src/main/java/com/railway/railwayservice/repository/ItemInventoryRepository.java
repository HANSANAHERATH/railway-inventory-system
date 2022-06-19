package com.railway.railwayservice.repository;

import com.railway.railwayservice.dtos.InventoryResponseDto;
import com.railway.railwayservice.entity.InventoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * The interface Item inventory repository.
 */
public interface ItemInventoryRepository extends JpaRepository<InventoryEntity, Long> {


    /**
     * Find all by inventory page.
     *
     * @param goodsId       the goods id
     * @param isDeleted     the is deleted
     * @param inventoryType the inventory type
     * @param paging        the paging
     * @return the page
     */
    @Query(value = "SELECT i.id as id, i.date as date, i.description as description, i.handover_to as handoverTo, i.quantity as quantity, i.shed_store_no as shedStoreNo, i.supervisor_name as supervisorName, i.time as time, i.unit_id as unit, g.total_quantity as totalQuantity, i.inventory_type as inventoryType\n" +
            "\tFROM railway.inventory i, railway.goods g WHERE i.goods_id = g.id AND i.goods_id= :goodsId AND g.is_deleted = :isDeleted AND i.inventory_type IN (:inventoryType) ORDER BY i.id ASC", nativeQuery = true)
    Page<InventoryResponseDto> findAllByInventory(@Param("goodsId") Long goodsId, @Param("isDeleted") boolean isDeleted, @Param("inventoryType") List<String> inventoryType, Pageable paging);
}
