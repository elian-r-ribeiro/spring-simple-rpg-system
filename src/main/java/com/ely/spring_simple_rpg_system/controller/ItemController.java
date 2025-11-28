package com.ely.spring_simple_rpg_system.controller;

import com.ely.spring_simple_rpg_system.dto.item.ItemCreationDto;
import com.ely.spring_simple_rpg_system.dto.item.ItemDto;
import com.ely.spring_simple_rpg_system.dto.item.ItemUpdateDto;
import com.ely.spring_simple_rpg_system.dto.player.PlayerDto;
import com.ely.spring_simple_rpg_system.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;

    @PostMapping(path = "/new-item", version = "1.0")
    private ResponseEntity<ItemDto> createNewItem(@RequestBody ItemCreationDto data) {
        return ResponseEntity.ok(itemService.createItem(data));
    }

    @GetMapping(version = "1.0")
    private ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping(path = "/{id}", version = "1.0")
    private ResponseEntity<ItemDto> getItemById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @PutMapping(path = "/update-item/{id}", version = "1.0")
    private ResponseEntity<ItemDto> updateItem(@PathVariable("id") Long id, @RequestBody ItemUpdateDto data) {
        return ResponseEntity.ok(itemService.updateItem(id, data));
    }

    @DeleteMapping(path = "/delete-item/{id}", version = "1.0")
    private ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
