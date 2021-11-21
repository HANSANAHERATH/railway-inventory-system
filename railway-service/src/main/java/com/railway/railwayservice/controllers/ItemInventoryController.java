package com.railway.railwayservice.controllers;

import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.service.ItemInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item-inventory")
public class ItemInventoryController {

    @Autowired
    private ItemInventory itemInventory;

    @GetMapping("/lookup")
    public ResponseEntity<ResponseWrapperDto> getItemLookup(@RequestParam String itemName) throws Exception {
        ResponseWrapperDto responseWrapperDto = itemInventory.getItemLookup(itemName);
        return ResponseEntity.ok(responseWrapperDto);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapperDto> createInventory(@RequestBody CreateInventoryDto createInventoryDto) throws Exception {
        ResponseWrapperDto responseWrapperDto = itemInventory.createInventory(createInventoryDto);
        return ResponseEntity.ok(responseWrapperDto);
    }

    @GetMapping("/getAll/{id}")
    public ResponseEntity<ResponseWrapperDto> getAllInventory(@PathVariable("id") Long id) throws Exception {
        ResponseWrapperDto responseWrapperDto = itemInventory.getAllInventory(id);
        return ResponseEntity.ok(responseWrapperDto);
    }

    /*@GetMapping()
    public ResponseEntity<ResponseWrapperDto> getItemAll(){
        ResponseWrapperDto responseWrapperDto = itemInventory.getItemAll();
        return ResponseEntity.ok(responseWrapperDto);
    }*/
}
