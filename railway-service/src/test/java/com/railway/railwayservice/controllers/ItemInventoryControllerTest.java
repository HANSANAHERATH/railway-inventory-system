package com.railway.railwayservice.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.enums.InventoryFilter;
import com.railway.railwayservice.enums.InventoryType;
import com.railway.railwayservice.service.ItemInventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ItemInventoryController.class})
@ExtendWith(SpringExtension.class)
class ItemInventoryControllerTest {
    @MockBean
    private ItemInventory itemInventory;

    @Autowired
    private ItemInventoryController itemInventoryController;

    @Test
    void testGetItemLookup() throws Exception {
        when(this.itemInventory.getItemLookup(anyLong())).thenReturn(new ResponseWrapperDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/item-inventory/lookup/{category}", 1L);
        MockMvcBuilders.standaloneSetup(this.itemInventoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":false,\"statusMessage\":null,\"body\":null}"));
    }

    @Test
    void testCreateInventory() throws Exception {
        when(this.itemInventory.createInventory((CreateInventoryDto) any())).thenReturn(new ResponseWrapperDto());

        CreateInventoryDto createInventoryDto = new CreateInventoryDto();
        createInventoryDto.setDate("2020-03-01");
        createInventoryDto.setDescription("The characteristics of someone or something");
        createInventoryDto.setGoodsId(123L);
        createInventoryDto.setHandoverTo("alice.liddell@example.org");
        createInventoryDto.setId(123L);
        createInventoryDto.setInventoryType(InventoryType.GOODS_IN);
        createInventoryDto.setQuantity(10.0f);
        createInventoryDto.setShedStoreNo("Shed Store No");
        createInventoryDto.setSupervisorName("Supervisor Name");
        createInventoryDto.setTime("Time");
        String content = (new ObjectMapper()).writeValueAsString(createInventoryDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/item-inventory/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.itemInventoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":false,\"statusMessage\":null,\"body\":null}"));
    }

    @Test
    void testGetAllInventory() throws Exception {
        when(this.itemInventory.getAllInventory((Long) any(), (InventoryFilter) any(), anyInt(), anyInt()))
                .thenReturn(new ResponseWrapperDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/item-inventory/getAll/{id}/{filter}/{size}/{page}", 123L, InventoryFilter.IN, 3, 1);
        MockMvcBuilders.standaloneSetup(this.itemInventoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":false,\"statusMessage\":null,\"body\":null}"));
    }
}

