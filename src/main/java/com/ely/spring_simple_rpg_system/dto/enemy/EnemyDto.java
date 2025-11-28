package com.ely.spring_simple_rpg_system.dto.enemy;

public record EnemyDto(
        Long id,
        String name,
        Long hp,
        Long ap,
        Long dp,
        Long xpDrop
) {
}
