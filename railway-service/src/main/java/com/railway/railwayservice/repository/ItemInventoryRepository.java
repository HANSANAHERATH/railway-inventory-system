package com.railway.railwayservice.repository;

import com.railway.railwayservice.dtos.InventoryResponseDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.InventoryEntity;
import com.railway.railwayservice.enums.InventoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ItemInventoryRepository  extends JpaRepository<InventoryEntity, Long> {


    @Query(value = "SELECT i.id as id, i.date as date, i.description as description, i.handover_to as handoverTo, i.quantity as quantity, i.shed_store_no as shedStoreNo, i.supervisor_name as supervisorName, i.time as time, i.unit_id as unit, g.total_quantity as totalQuantity, i.inventory_type as inventoryType\n" +
            "\tFROM railway.inventory i, railway.goods g WHERE i.goods_id = g.id AND i.goods_id= :goodsId AND g.is_deleted = :isDeleted AND i.inventory_type IN (:inventoryType) ORDER BY i.id ASC", nativeQuery = true)
    Page<InventoryResponseDto> findAllByInventory(@Param("goodsId") Long goodsId, @Param("isDeleted") boolean isDeleted, @Param("inventoryType") List<String> inventoryType, Pageable paging);
}
