package com.ely.spring_simple_rpg_system.controller;

import com.ely.spring_simple_rpg_system.dto.enemy.EnemyCreationDto;
import com.ely.spring_simple_rpg_system.dto.enemy.EnemyDto;
import com.ely.spring_simple_rpg_system.dto.enemy.EnemyUpdateDto;
import com.ely.spring_simple_rpg_system.service.EnemyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enemies")
@AllArgsConstructor
public class EnemyController {

    private EnemyService enemyService;

    @PostMapping(path = "/new-enemy", version = "1.0")
    private ResponseEntity<EnemyDto> createEnemy(@RequestBody @Valid EnemyCreationDto data) {
        return ResponseEntity.ok(enemyService.createEnemy(data));
    }

    @GetMapping(version = "1.0")
    private ResponseEntity<List<EnemyDto>> getAllEnemies() {
        return ResponseEntity.ok(enemyService.getAllEnemies());
    }

    @GetMapping(path = "/{id}", version = "1.0")
    private ResponseEntity<EnemyDto> getEnemyById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(enemyService.getEnemyById(id));
    }

    @PutMapping(path = "/update-enemy/{id}", version = "1.0")
    private ResponseEntity<EnemyDto> updateEnemy(@PathVariable("id") Long id, @RequestBody EnemyUpdateDto data) {
        return ResponseEntity.ok(enemyService.updateEnemy(id, data));
    }

    @DeleteMapping(path = "/delete-enemy/{id}", version = "1.0")
    private ResponseEntity<Void> deleteEnemy(@PathVariable("id") Long id) {
        enemyService.deleteEnemy(id);
        return ResponseEntity.noContent().build();
    }
}
