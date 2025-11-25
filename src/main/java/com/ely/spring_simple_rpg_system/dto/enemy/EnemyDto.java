package com.ely.spring_simple_rpg_system.dto.enemy;

public record EnemyDto(
        Long enemyId,
        String enemyName,
        Long enemyHp,
        Long enemyAp,
        Long enemyDp,
        Long enemyXpDrop
) {
}
