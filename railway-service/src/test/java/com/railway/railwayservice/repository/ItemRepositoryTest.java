package com.railway.railwayservice.repository;

import com.railway.railwayservice.dtos.GoodsEntityResponseDto;
import com.railway.railwayservice.entity.CategoryEntity;
import com.railway.railwayservice.entity.GoodsEntity;
import com.railway.railwayservice.entity.InventoryEntity;
import com.railway.railwayservice.entity.UnitsEntity;
import com.railway.railwayservice.enums.InventoryType;
import org.hibernate.annotations.SQLInsert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({"/masterData.sql"})
@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void findGoodsEntitiesTest() {

        UnitsEntity unitsEntity = new UnitsEntity();
        unitsEntity.setId(1L);
        unitsEntity.setName("");

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("");

        InventoryEntity inventoryEntity1 = new InventoryEntity();
        inventoryEntity1.setDate(LocalDate.now());
        inventoryEntity1.setDescription("");
        inventoryEntity1.setUnitsEntity(unitsEntity);
        inventoryEntity1.setId(123L);
        inventoryEntity1.setHandoverTo("");
        inventoryEntity1.setQuantity(100);
        inventoryEntity1.setShedStoreNo("");
        inventoryEntity1.setSupervisorName("");
        inventoryEntity1.setTime(LocalTime.now());
        inventoryEntity1.setInventoryType(InventoryType.GOODS_IN);

        InventoryEntity inventoryEntity2 = new InventoryEntity();
        inventoryEntity2.setDate(LocalDate.now());
        inventoryEntity2.setDescription("");
        inventoryEntity2.setUnitsEntity(unitsEntity);
        inventoryEntity2.setId(123L);
        inventoryEntity2.setHandoverTo("");
        inventoryEntity2.setQuantity(100);
        inventoryEntity2.setShedStoreNo("");
        inventoryEntity2.setSupervisorName("");
        inventoryEntity2.setTime(LocalTime.now());
        inventoryEntity2.setInventoryType(InventoryType.GOODS_IN);

        Set<InventoryEntity> inventoryEntities = new HashSet<>();
        inventoryEntities.add(inventoryEntity1);
        inventoryEntities.add(inventoryEntity2);

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setCategoryEntity(categoryEntity);
        goodsEntity.setDate(LocalDate.now());
        goodsEntity.setInventoryEntity(inventoryEntities);
        goodsEntity.setDeleted(false);
        goodsEntity.setUnitsEntity(unitsEntity);
        goodsEntity.setDescription("");
        goodsEntity.setId(123L);
        goodsEntity.setMinQuantity(0);
        goodsEntity.setName("");
        goodsEntity.setTotalQuantity(1000);

        itemRepository.save(goodsEntity);

        Pageable paging = PageRequest.of(0, 10, Sort.by("name"));
        Page<GoodsEntityResponseDto> result = itemRepository.findGoodsEntities(categoryEntity, false, paging);

        assertThat(result.getContent().size()).isEqualTo(1);
    }

    @Test
    void findGoodsList() {
    }
}