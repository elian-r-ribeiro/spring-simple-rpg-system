package com.ely.spring_simple_rpg_system.converter;

import com.ely.spring_simple_rpg_system.dto.PlayerCreationDto;
import com.ely.spring_simple_rpg_system.dto.PlayerDto;
import com.ely.spring_simple_rpg_system.entity.Player;

public class PlayerConverter {

    public static Player fromCreationDtoToPlayer(PlayerCreationDto dto) {

        return new Player(
                null,
                dto.playerName(),
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
                player.getPlayerId(),
                player.getPlayerName(),
                player.getPlayerAttackPower(),
                player.getPlayerDefensePower(),
                player.getPlayerCurrentHp(),
                player.getPlayerMaxHp(),
                player.getPlayerLevel(),
                player.getPlayerXp(),
                player.getPlayerItems()
        );
    }
}
