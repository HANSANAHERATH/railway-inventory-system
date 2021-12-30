package com.railway.railwayservice.controllers;

import com.railway.railwayservice.dtos.GoodsCreateRequestDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseWrapperDto> getItem(@PathVariable("id") long id){
        ResponseWrapperDto responseWrapperDto = itemService.getItem(id);
        return ResponseEntity.ok(responseWrapperDto);
    }

    @GetMapping(path = "/all/{id}/{size}/{page}")
    public ResponseEntity<ResponseWrapperDto> getAllItem(@PathVariable("id") int categoryId, @PathVariable("size") int size, @PathVariable("page") int page){
        ResponseWrapperDto responseWrapperDto = itemService.getAllItem(categoryId, size, page);
        return ResponseEntity.ok(responseWrapperDto);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapperDto> createItem(@RequestBody GoodsCreateRequestDto goodsCreateRequestDto){
        ResponseWrapperDto responseWrapperDto = itemService.createItem(goodsCreateRequestDto);
        return ResponseEntity.ok(responseWrapperDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseWrapperDto> updateItem(@RequestBody GoodsCreateRequestDto goodsCreateRequestDto, @PathVariable("id") long id){
        ResponseWrapperDto responseWrapperDto = itemService.updateItem(goodsCreateRequestDto, id);
        return ResponseEntity.ok(responseWrapperDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseWrapperDto> deleteItem(@PathVariable("id") long id){
        ResponseWrapperDto responseWrapperDto = itemService.deleteItem(id);
        return ResponseEntity.ok(responseWrapperDto);
    }

    @GetMapping(path = "/unitList")
    public ResponseEntity<ResponseWrapperDto> getUnitList(){
        ResponseWrapperDto responseWrapperDto = itemService.getUnitList();
        return ResponseEntity.ok(responseWrapperDto);
    }

    @GetMapping(path = "/itemCategoryList")
    public ResponseEntity<ResponseWrapperDto> getItemCategoryList(){
        ResponseWrapperDto responseWrapperDto = itemService.getItemCategoryList();
        return ResponseEntity.ok(responseWrapperDto);
    }
}
