package com.railway.railwayservice.controllers;

import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.service.ItemInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item-inventory")
public class ItemInventoryController {

    @Autowired
    private ItemInventory itemInventory;

    @GetMapping()
    public ResponseEntity<ResponseWrapperDto> getItemAll(){
        ResponseWrapperDto responseWrapperDto = itemInventory.getItemAll();
        return ResponseEntity.ok(responseWrapperDto);
    }
}
