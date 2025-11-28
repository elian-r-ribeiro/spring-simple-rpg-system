package com.ely.spring_simple_rpg_system.controller;

import com.ely.spring_simple_rpg_system.dto.item.ItemsListDto;
import com.ely.spring_simple_rpg_system.dto.player.PlayerCreationDto;
import com.ely.spring_simple_rpg_system.dto.player.PlayerDto;
import com.ely.spring_simple_rpg_system.dto.player.PlayerUpdateDto;
import com.ely.spring_simple_rpg_system.service.PlayerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class PlayerController {

    private PlayerService playerService;

    @PostMapping(path = "/new-player", version = "1.0")
    private ResponseEntity<PlayerDto> createPlayer(@RequestBody @Valid PlayerCreationDto data) {
        return ResponseEntity.ok(playerService.createPlayer(data));
    }

    @PostMapping(path = "/add-item-to-player/{playerId}/{itemId}", version = "1.0")
    private ResponseEntity<PlayerDto> addItemToPlayerItems(@PathVariable("playerId") Long playerId,
                                                           @PathVariable("itemId") Long itemId) {
        return ResponseEntity.ok(playerService.addItemToPlayerItems(playerId, itemId));
    }

    @GetMapping(version = "1.0")
    private ResponseEntity<Set<PlayerDto>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping(path = "/{id}", version = "1.0")
    private ResponseEntity<PlayerDto> getPlayerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @GetMapping(path = "/get-player-items/{id}", version = "1.0")
    private ResponseEntity<ItemsListDto> getPlayerItems(@PathVariable("id") Long id) {
        return ResponseEntity.ok(playerService.getPlayerItems(id));
    }

    @PutMapping(path = "/update-player/{id}", version = "1.0")
    private ResponseEntity<PlayerDto> updatePlayer(@RequestBody PlayerUpdateDto data, @PathVariable("id") Long id) {
        return ResponseEntity.ok(playerService.updatePlayer(data, id));
    }

    @DeleteMapping(path = "/delete-player/{id}", version = "1.0")
    private ResponseEntity<Void> deletePlayer(@PathVariable("id") Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
