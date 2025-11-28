package com.ely.spring_simple_rpg_system.controller;

import com.ely.spring_simple_rpg_system.dto.item.ItemsListDto;
import com.ely.spring_simple_rpg_system.dto.player.PlayerDto;
import com.ely.spring_simple_rpg_system.service.PlayerItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players-items")
@AllArgsConstructor
public class PlayerItemController {

    private PlayerItemService playerItemService;

    @PostMapping(path = "/add-item-to-player/{playerId}/{itemId}", version = "1.0")
    private ResponseEntity<PlayerDto> addItemToPlayerItems(@PathVariable("playerId") Long playerId,
                                                           @PathVariable("itemId") Long itemId) {
        return ResponseEntity.ok(playerItemService.addItemToPlayerItems(playerId, itemId));
    }

    @PostMapping(path = "/use-item/{playerId}/{itemPositionInList}", version = "1.0")
    private ResponseEntity<PlayerDto> useItem(@PathVariable("playerId") Long playerId,
                                              @PathVariable("itemPositionInList") int itemPositionInList) {
        return ResponseEntity.ok(playerItemService.useItem(playerId, itemPositionInList));
    }

    @GetMapping(path = "/get-player-items/{id}", version = "1.0")
    private ResponseEntity<ItemsListDto> getPlayerItems(@PathVariable("id") Long id) {
        return ResponseEntity.ok(playerItemService.getPlayerItems(id));
    }
}
