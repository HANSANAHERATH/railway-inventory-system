package com.railway.railwayservice.controllers;

import com.railway.railwayservice.dtos.ItemCreateRequestDto;
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

    @GetMapping
    public ResponseEntity<ResponseWrapperDto> getAllItem(){
        ResponseWrapperDto responseWrapperDto = itemService.getAllItem();
        return ResponseEntity.ok(responseWrapperDto);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapperDto> createItem(@RequestBody ItemCreateRequestDto itemCreateRequestDto){
        ResponseWrapperDto responseWrapperDto = itemService.createItem(itemCreateRequestDto);
        return ResponseEntity.ok(responseWrapperDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseWrapperDto> updateItem(@RequestBody ItemCreateRequestDto itemCreateRequestDto, @PathVariable("id") long id){
        ResponseWrapperDto responseWrapperDto = itemService.updateItem(itemCreateRequestDto, id);
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
}
