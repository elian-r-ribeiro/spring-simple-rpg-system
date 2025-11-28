package com.ely.spring_simple_rpg_system.converter;

import com.ely.spring_simple_rpg_system.dto.player.PlayerCreationDto;
import com.ely.spring_simple_rpg_system.dto.player.PlayerDto;
import com.ely.spring_simple_rpg_system.entity.Player;

public class PlayerConverter {

    public static Player fromCreationDtoToPlayer(PlayerCreationDto dto) {

        return new Player(
                null,
                dto.name(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static PlayerDto fromPlayerToDto(Player player) {
        return new PlayerDto(
                player.getId(),
                player.getName(),
                player.getAttackPower(),
                player.getDefensePower(),
                player.getCurrentHp(),
                player.getMaxHp(),
                player.getLevel(),
                player.getXp(),
                player.getRequiredXpToNextLevel(),
                player.getItems()
        );
    }
}
