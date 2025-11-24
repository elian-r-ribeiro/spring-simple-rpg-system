package com.ely.spring_simple_rpg_system.service;

import com.ely.spring_simple_rpg_system.converter.PlayerConverter;
import com.ely.spring_simple_rpg_system.dto.PlayerCreationDto;
import com.ely.spring_simple_rpg_system.dto.PlayerDto;
import com.ely.spring_simple_rpg_system.entity.Player;
import com.ely.spring_simple_rpg_system.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerService {

    private PlayerRepository playerRepository;

    public PlayerDto createPlayer(PlayerCreationDto data) {
        Player newPlayer = playerRepository.save(PlayerConverter.fromCreationDtoToPlayer(data));

        return PlayerConverter.fromPlayerToDto(newPlayer);
    }
}
