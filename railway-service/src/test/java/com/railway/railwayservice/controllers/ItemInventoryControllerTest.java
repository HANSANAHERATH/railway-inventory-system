package com.railway.railwayservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.enums.InventoryFilter;
import com.railway.railwayservice.service.ItemInventory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * The type Item inventory controller test.
 */
@ContextConfiguration(classes = {ItemInventoryController.class})
@ExtendWith(SpringExtension.class)
class ItemInventoryControllerTest {

    @MockBean
    private ItemInventory itemInventory;

    private ItemInventoryController itemInventoryController;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        itemInventoryController = new ItemInventoryController(itemInventory);
    }

    /**
     * Tear down.
     */
    @AfterEach
    void tearDown() {
    }

    /**
     * Gets item lookup.
     *
     * @throws Exception the exception
     */
    @Test
    void getItemLookup() throws Exception {
        when(this.itemInventory.getItemLookup(anyLong())).thenReturn(new ResponseWrapperDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/item-inventory/lookup/{category}", 1L);
        MockMvcBuilders.standaloneSetup(this.itemInventoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Create inventory.
     *
     * @throws Exception the exception
     */
    @Test
    void createInventory() throws Exception {
        when(this.itemInventory.createInventory(any())).thenReturn(new ResponseWrapperDto());

        CreateInventoryDto createInventoryDto = new CreateInventoryDto();

        String content = (new ObjectMapper()).writeValueAsString(createInventoryDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/item-inventory/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.itemInventoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Gets all inventory.
     *
     * @throws Exception the exception
     */
    @Test
    void getAllInventory() throws Exception {
        when(this.itemInventory.getAllInventory(any(), any(), anyInt(), anyInt()))
                .thenReturn(new ResponseWrapperDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/item-inventory/getAll/{id}/{filter}/{size}/{page}", 123L, InventoryFilter.IN, 3, 1);
        MockMvcBuilders.standaloneSetup(this.itemInventoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
}