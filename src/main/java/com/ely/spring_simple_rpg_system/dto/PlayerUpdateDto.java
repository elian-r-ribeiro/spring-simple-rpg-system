package com.ely.spring_simple_rpg_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlayerUpdateDto(

        @NotBlank(message = "Forneça o novo nome do jogador.")
        @Size(min = 3, message = "O nome do jogador deve ter pelo menos 3 caracteres.")
        @Size(message = "O nome do jogador deve ter no máximo 20 caracteres.")
        String playerName
) {
}
