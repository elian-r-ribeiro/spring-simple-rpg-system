package com.ely.spring_simple_rpg_system.converter;

import com.ely.spring_simple_rpg_system.dto.enemy.EnemyCreationDto;
import com.ely.spring_simple_rpg_system.dto.enemy.EnemyDto;
import com.ely.spring_simple_rpg_system.dto.enemy.EnemyUpdateDto;
import com.ely.spring_simple_rpg_system.entity.Enemy;

public class EnemyConverter {

    public static Enemy fromCreationDtoToEnemy(EnemyCreationDto dto) {
        return new Enemy(
                null,
                dto.name(),
                dto.hp(),
                dto.ap(),
                dto.dp(),
                dto.xpDrop()
        );
    }

    public static EnemyDto fromEnemyToDto(Enemy enemy) {
        return new EnemyDto(
                enemy.getId(),
                enemy.getName(),
                enemy.getHp(),
                enemy.getAp(),
                enemy.getDp(),
                enemy.getXpDrop()
        );
    }

    public static void mergeChanges(Enemy enemy, EnemyUpdateDto dto) {
        if(dto.name() != null) enemy.setName(dto.name());
        if(dto.hp() != null) enemy.setHp(dto.hp());
        if(dto.ap() != null) enemy.setAp(dto.ap());
        if(dto.dp() != null) enemy.setDp(dto.dp());
        if(dto.xpDrop() != null) enemy.setXpDrop(dto.xpDrop());
    }
}
