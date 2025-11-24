package com.ely.spring_simple_rpg_system.dto;

import com.ely.spring_simple_rpg_system.entity.Item;
import java.util.List;

public record PlayerDto(
        Long playerId,

        String playerName,

        Long playerAttackPower,

        Long playerDefensePower,

        Long playerCurrentHp,

        Long playerMaxHp,

        Long playerLevel,

        Long playerXp,

        List<Item> playerItems
) {
}
