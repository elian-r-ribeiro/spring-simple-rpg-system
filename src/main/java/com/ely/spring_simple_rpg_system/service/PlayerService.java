package com.ely.spring_simple_rpg_system.service;

import com.ely.spring_simple_rpg_system.converter.PlayerConverter;
import com.ely.spring_simple_rpg_system.dto.PlayerCreationDto;
import com.ely.spring_simple_rpg_system.dto.PlayerDto;
import com.ely.spring_simple_rpg_system.dto.PlayerUpdateDto;
import com.ely.spring_simple_rpg_system.entity.Player;
import com.ely.spring_simple_rpg_system.exception.PlayerNotFoundException;
import com.ely.spring_simple_rpg_system.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerService {

    private PlayerRepository playerRepository;

    public PlayerDto createPlayer(PlayerCreationDto data) {
        Player newPlayer = playerRepository.save(PlayerConverter.fromCreationDtoToPlayer(data));

        return PlayerConverter.fromPlayerToDto(newPlayer);
    }

    public Set<PlayerDto> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(PlayerConverter::fromPlayerToDto)
                .collect(Collectors.toSet());
    }

    public PlayerDto getPlayerById(Long id) {
        return PlayerConverter.fromPlayerToDto(findPlayerByIdOrThrowException(id));
    }

    public PlayerDto updatePlayer(PlayerUpdateDto data, Long id) {
        final Player player = findPlayerByIdOrThrowException(id);

        player.setPlayerName(data.playerName());

        return PlayerConverter.fromPlayerToDto(playerRepository.save(player));
    }

    public void deletePlayer(Long id) {
        final Player player = findPlayerByIdOrThrowException(id);
        playerRepository.deleteById(id);
    }

    private Player findPlayerByIdOrThrowException(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
    }
}
