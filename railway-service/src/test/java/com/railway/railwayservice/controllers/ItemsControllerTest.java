package com.railway.railwayservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railway.railwayservice.dtos.GoodsCreateRequestDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

/**
 * The type Items controller test.
 */
@ContextConfiguration(classes = {ItemsController.class})
@ExtendWith(SpringExtension.class)
class ItemsControllerTest {
    @MockBean
    private ItemService itemService;

    @Autowired
    private ItemsController itemsController;

    /**
     * Test get item.
     *
     * @throws Exception the exception
     */
    @Test
    void testGetItem() throws Exception {
        when(this.itemService.getItem(anyLong())).thenReturn(new ResponseWrapperDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.itemsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Test get all item.
     *
     * @throws Exception the exception
     */
    @Test
    void testGetAllItem() throws Exception {
        when(this.itemService.getAllItem(anyInt(), anyInt(), anyInt())).thenReturn(new ResponseWrapperDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/all/{id}/{size}/{page}", 1, 3, 1);
        MockMvcBuilders.standaloneSetup(this.itemsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Test update item.
     *
     * @throws Exception the exception
     */
    @Test
    void testUpdateItem() throws Exception {
        when(this.itemService.updateItem((GoodsCreateRequestDto) any(), anyLong())).thenReturn(new ResponseWrapperDto());

        GoodsCreateRequestDto goodsCreateRequestDto = new GoodsCreateRequestDto();
        goodsCreateRequestDto.setCategory(1);
        goodsCreateRequestDto.setDate("2020-03-01");
        goodsCreateRequestDto.setDescription("The characteristics of someone or something");
        goodsCreateRequestDto.setGoodName("Good Name");
        goodsCreateRequestDto.setId(123L);
        goodsCreateRequestDto.setMinQuantity(10.0f);
        goodsCreateRequestDto.setUnitId(123L);
        goodsCreateRequestDto.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(goodsCreateRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/items/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.itemsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Test create item.
     *
     * @throws Exception the exception
     */
    @Test
    void testCreateItem() throws Exception {
        GoodsCreateRequestDto goodsCreateRequestDto = new GoodsCreateRequestDto();
        goodsCreateRequestDto.setCategory(1);
        goodsCreateRequestDto.setDate("2020-03-01");
        goodsCreateRequestDto.setDescription("The characteristics of someone or something");
        goodsCreateRequestDto.setGoodName("Good Name");
        goodsCreateRequestDto.setId(123L);
        goodsCreateRequestDto.setMinQuantity(10.0f);
        goodsCreateRequestDto.setUnitId(123L);
        goodsCreateRequestDto.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(goodsCreateRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.itemsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Test delete item.
     *
     * @throws Exception the exception
     */
    @Test
    void testDeleteItem() throws Exception {
        when(this.itemService.deleteItem(anyLong())).thenReturn(new ResponseWrapperDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/items/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.itemsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Test get unit list.
     *
     * @throws Exception the exception
     */
    @Test
    void testGetUnitList() throws Exception {
        when(this.itemService.getUnitList()).thenReturn(new ResponseWrapperDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/unitList");
        MockMvcBuilders.standaloneSetup(this.itemsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Test get item category list.
     *
     * @throws Exception the exception
     */
    @Test
    void testGetItemCategoryList() throws Exception {
        when(this.itemService.getItemCategoryList()).thenReturn(new ResponseWrapperDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/itemCategoryList");
        MockMvcBuilders.standaloneSetup(this.itemsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
}

