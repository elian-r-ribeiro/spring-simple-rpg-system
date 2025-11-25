package com.ely.spring_simple_rpg_system.converter;

import com.ely.spring_simple_rpg_system.dto.enemy.EnemyCreationDto;
import com.ely.spring_simple_rpg_system.dto.enemy.EnemyDto;
import com.ely.spring_simple_rpg_system.dto.enemy.EnemyUpdateDto;
import com.ely.spring_simple_rpg_system.entity.Enemy;

public class EnemyConverter {

    public static Enemy fromCreationDtoToEnemy(EnemyCreationDto dto) {
        return new Enemy(
                null,
                dto.enemyName(),
                dto.enemyHp(),
                dto.enemyAp(),
                dto.enemyDp(),
                dto.enemyXpDrop()
        );
    }

    public static EnemyDto fromEnemyToDto(Enemy enemy) {
        return new EnemyDto(
                enemy.getEnemyId(),
                enemy.getEnemyName(),
                enemy.getEnemyHp(),
                enemy.getEnemyAp(),
                enemy.getEnemyDp(),
                enemy.getEnemyXpDrop()
        );
    }

    public static void mergeChanges(Enemy enemy, EnemyUpdateDto dto) {
        if(dto.enemyName() != null) enemy.setEnemyName(dto.enemyName());
        if(dto.enemyHp() != null) enemy.setEnemyHp(dto.enemyHp());
        if(dto.enemyAp() != null) enemy.setEnemyAp(dto.enemyAp());
        if(dto.enemyDp() != null) enemy.setEnemyDp(dto.enemyDp());
        if(dto.enemyXpDrop() != null) enemy.setEnemyXpDrop(dto.enemyXpDrop());
    }
}
