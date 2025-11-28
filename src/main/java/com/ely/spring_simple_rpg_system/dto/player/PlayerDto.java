package com.ely.spring_simple_rpg_system.dto.player;

import com.ely.spring_simple_rpg_system.entity.Item;
import java.util.List;

public record PlayerDto(
        Long id,

        String name,

        Long attackPower,

        Long defensePower,

        Long currentHp,

        Long maxHp,

        Long level,

        Long xp,

        Long requiredXpToNextLevel,

        List<Item> playerItems
) {
}
