package com.ely.spring_simple_rpg_system.dto.player;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlayerCreationDto(

        @NotBlank(message = "O nome de usuário é obrigatório.")
        @Size(min = 3, message = "O nome de usuário deve ter no mínimo 3 caracteres.")
        @Size(max = 20, message = "O nome de usuário deve ter no máximo 20 caracteres.")
        String playerName
) {
}
