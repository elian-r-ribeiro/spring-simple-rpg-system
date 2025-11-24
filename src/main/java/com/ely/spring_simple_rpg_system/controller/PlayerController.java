package com.ely.spring_simple_rpg_system.controller;

import com.ely.spring_simple_rpg_system.dto.ErrorDto;
import com.ely.spring_simple_rpg_system.dto.PlayerCreationDto;
import com.ely.spring_simple_rpg_system.dto.PlayerDto;
import com.ely.spring_simple_rpg_system.service.PlayerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class PlayerController {

    private PlayerService playerService;

    @PostMapping(path = "/new-player", version = "1.0")
    private PlayerDto createPlayer(@RequestBody @Valid PlayerCreationDto data) {

        return playerService.createPlayer(data);
    }
}
