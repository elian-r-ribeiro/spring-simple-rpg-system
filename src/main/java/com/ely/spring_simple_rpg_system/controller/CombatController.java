package com.ely.spring_simple_rpg_system.controller;

import com.ely.spring_simple_rpg_system.service.CombatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/combats")
@AllArgsConstructor
public class CombatController {

    private CombatService combatService;

    @PostMapping(value = "/begin-combat/{playerId}/{enemyId}", version = "1.0")
    private ResponseEntity<List<String>> executeCombat(@PathVariable("playerId") Long playerId,
                                                       @PathVariable("enemyId") Long enemyId) {
        return ResponseEntity.ok(combatService.executeCombat(playerId, enemyId));
    }
}
